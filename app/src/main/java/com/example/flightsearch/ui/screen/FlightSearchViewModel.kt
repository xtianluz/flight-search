package com.example.flightsearch.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightsearch.data.*
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
    var selectedFavoriteList = mutableListOf(LocalData.selectedFavorite)


    init {
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
            uiState = UiState.Default
        }
    }

    fun removeFromFavorite() {
        viewModelScope.launch {
            println("$selectedFavoriteList")
        }
    }

    private suspend fun getSearchResult(): List<Airport> =
        flightSearchRepository.getSearchResult(userInput)
}





