package com.example.flightsearch.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightsearch.data.*
import kotlinx.coroutines.launch

sealed interface UiState {
    data class Result(val searchResult: List<Airport>) : UiState
    data class Favorite(val favoriteList: List<SelectedFavorite>) : UiState
    object Default : UiState
}

class FlightSearchViewModel(
    private val flightSearchRepository: FlightSearchRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    var userInput: String by mutableStateOf("")
        private set
    var uiState: UiState by mutableStateOf(UiState.Default)
        private set
    private var flightList: List<Airport> by mutableStateOf(emptyList())//to display as result of search
    private var favoriteCodeList: List<Favorite> by mutableStateOf(emptyList())//list of existing favorite codes to be used for fetching from airport
    private var departureFlight: Airport by mutableStateOf(LocalData.singleAirport)
    private var destinationFlight: Airport by mutableStateOf(LocalData.singleAirport)
    private var foundFavorite: Favorite by mutableStateOf(LocalData.singleFavorite)
    private var selectedFavoriteList = mutableStateListOf(LocalData.selectedFavorite)//list of favorite pairs with complete detail

    var removedOrNot: Boolean by mutableStateOf(false)

    init {
        launchSavedSearch()//from Preferences DataStore
    }

    private fun launchSavedSearch() {
        viewModelScope.launch {
            userPreferencesRepository.getSavedUserInput().collect {
                userInput = it
                if(userInput.isNotBlank()){
                    flightList = flightSearchRepository.getSearchResult(userInput)
                    uiState = UiState.Result(flightList)
                }else{
                    selectedFavoriteList.clear()
                    launchFavorite()
                }
            }
        }
    }

    //first get the data saved from favorite table
    //for each code fetch it from airport table
    //then add to the list to be displayed
    private fun launchFavorite() {
        viewModelScope.launch {
            favoriteCodeList = flightSearchRepository.getAllFavorites()
            favoriteCodeList.forEach {
                departureFlight = flightSearchRepository.getDepartureFlight(it.departure_code)
                destinationFlight = flightSearchRepository.getDepartureFlight(it.destination_code)
                selectedFavoriteList.add(
                    SelectedFavorite(
                        id = it.id + (0..1000).random(),
                        departureCode = departureFlight.iata_code,
                        departureName = departureFlight.name,
                        destinationCode = destinationFlight.iata_code,
                        destinationName = destinationFlight.name
                    )
                )
            }
            selectedFavoriteList.remove(LocalData.selectedFavorite)
            uiState = UiState.Favorite(selectedFavoriteList)
        }
    }

    private suspend fun saveUserInput(userInput: String) {
        userPreferencesRepository.saveUserInputPreferences(userInput)
    }
    private suspend fun getSavedUserData() {
        userPreferencesRepository.getSavedUserInput().collect {
            userInput = it
        }
    }
    private suspend fun searchResult() {
        flightList = getSearchResult()
        uiState = UiState.Result(flightList)
    }
    private suspend fun getSearchResult(): List<Airport> =
        flightSearchRepository.getSearchResult(userInput)

    fun updateUserInput(newUserInput: String) {
        userInput = newUserInput
        viewModelScope.launch {
            saveUserInput(userInput)
        }
        if (userInput.isNotBlank()) {
            viewModelScope.launch {
                searchResult()

            }
        } else if (userInput == "") {
            selectedFavoriteList.clear()
            launchFavorite()
        } else {
            uiState = UiState.Default
        }
    }
    fun clearUserInput() {
        userInput = ""
    }
    suspend fun removeFromFavoriteWithQuery(
        departureCode: String,
        destinationCode: String
    ) {
        findOneFavorite(
            departureCode = departureCode,
            destinationCode = destinationCode,
        )
        //Just ignore warning, condition always true because of initial value, until fetching data from database
        if (foundFavorite != null) {
            removeFromFavorite(
                favorite = foundFavorite
            )
            selectedFavoriteList.removeIf {
                it.departureCode == foundFavorite.departure_code && it.destinationCode == foundFavorite.destination_code
            }
            removedOrNot = true
            uiState = UiState.Favorite(selectedFavoriteList)
        } else {
            removedOrNot = false
        }
    }
    private suspend fun findOneFavorite(departureCode: String, destinationCode: String) {
        foundFavorite = flightSearchRepository.findOneFavorite(
            departureCode = departureCode,
            destinationCode = destinationCode
        )
    }
    private suspend fun removeFromFavorite(favorite: Favorite) {
        flightSearchRepository.removeFromFavorite(
            favorite = favorite
        )
    }
}




