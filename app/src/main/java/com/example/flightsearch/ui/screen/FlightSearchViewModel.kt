package com.example.flightsearch.ui.screen

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.FlightSearchRepository
import kotlinx.coroutines.flow.Flow

class FlightSearchViewModel(
    private val flightSearchRepository: FlightSearchRepository
): ViewModel(){


    var searchUiState: FlightSearchUiState by mutableStateOf(FlightSearchUiState.Empty)

    var userInput: String by mutableStateOf("")
        private set

    fun updateUserInput(newUserInput: String){
        userInput = newUserInput
        getAllSearch(userInput)
    }
    fun getAllItems(): Flow<List<Airport>> = flightSearchRepository.getAllItems()

    fun getAllSearch(userInput: String): Flow<List<Airport>> = flightSearchRepository.getAllSearch(userInput)

}


sealed interface FlightSearchUiState{
    data class Result(val searchedItems: List<Airport>) : FlightSearchUiState
    object Empty: FlightSearchUiState
}