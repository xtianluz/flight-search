package com.example.flightsearch.ui.screen

import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightsearch.data.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface UiState {
    data class Result(val searchResult: List<Airport>) : UiState
    data class Favorite(val favoriteList: List<SelectedFavorite>) : UiState
    object Default: UiState
}

class FlightSearchViewModel(
    savedStateHandle: SavedStateHandle,
    private val flightSearchRepository: FlightSearchRepository,
    ) : ViewModel() {

    var userInput: String by mutableStateOf("")
        private set
    var uiState: UiState by mutableStateOf(UiState.Default)
        private set
    private var flightList: List<Airport> by mutableStateOf(emptyList())
    private var favoriteCodeList: List<Favorite> by mutableStateOf(emptyList())
    private var departureFlight: Airport by mutableStateOf(LocalData.singleAirport)
    private var destinationFlight: Airport by mutableStateOf(LocalData.singleAirport)
    private var foundFavorite: Favorite by mutableStateOf(LocalData.singleFavorite)
    private var selectedFavoriteList = mutableStateListOf(LocalData.selectedFavorite)

    var removedOrNot: Boolean by mutableStateOf(false)

    init {
        launchFavorite()
    }

    private fun launchFavorite(){
        viewModelScope.launch {
            favoriteCodeList = flightSearchRepository.getAllFavorites()
            favoriteCodeList.forEach{
                departureFlight = flightSearchRepository.getDepartureFlight(it.departure_code)
                destinationFlight = flightSearchRepository.getDepartureFlight(it.destination_code)
                selectedFavoriteList.add(
                    SelectedFavorite(
                        id = it.id,
                        departureCode = departureFlight.iata_code,
                        departureName = departureFlight.name,
                        destinationCode = destinationFlight.iata_code,
                        destinationName = destinationFlight.name
                    )
                )
            }
            selectedFavoriteList.removeFirst()
            uiState = UiState.Favorite(selectedFavoriteList)
        }
    }

    private suspend fun searchResult() {
        flightList = getSearchResult()
        uiState = UiState.Result(flightList)
    }

    fun updateUserInput(newUserInput: String) {
        userInput = newUserInput
        if (userInput.isNotBlank()) {
            viewModelScope.launch {
                searchResult()
            }
        } else {
            uiState = UiState.Favorite(selectedFavoriteList)
        }
    }
    suspend fun removeFromFavoriteWithQuery(
        departureCode: String,
        destinationCode: String
    ){
        findOneFavorite(
            departureCode = departureCode,
            destinationCode = destinationCode,
        )
        if(foundFavorite != null) {
                removeFromFavorite(
                    favorite = foundFavorite
                )
                selectedFavoriteList.removeIf {
                    it.departureCode == foundFavorite.departure_code && it.destinationCode == foundFavorite.destination_code
                }
                removedOrNot = true
                uiState = UiState.Favorite(selectedFavoriteList)
        }
        else{
            removedOrNot = false
        }
    }
    private suspend fun findOneFavorite(departureCode: String, destinationCode: String) {
        foundFavorite = flightSearchRepository.findOneFavorite(
            departureCode = departureCode,
            destinationCode = destinationCode
        )
    }
    private suspend fun removeFromFavorite(favorite: Favorite){
        flightSearchRepository.removeFromFavorite(
            favorite = favorite
        )
    }
    private suspend fun getSearchResult(): List<Airport> =
        flightSearchRepository.getSearchResult(userInput)
}




