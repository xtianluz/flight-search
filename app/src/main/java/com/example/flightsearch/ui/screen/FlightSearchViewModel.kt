package com.example.flightsearch.ui.screen

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.FlightSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface UiState{
    data class Result(val searchResult: List<Airport>) : UiState
    object DefaultState : UiState
}

class FlightSearchViewModel(
    private val flightSearchRepository: FlightSearchRepository
): ViewModel(){

    var userInput: String by mutableStateOf("")
        private set

    var uiState: UiState by mutableStateOf(UiState.DefaultState)
        private set

    init{
        viewModelScope.launch{

        }
    }

    private suspend fun getResult(){
        val result = getAllItems()
        uiState = UiState.Result(result)
    }

    private suspend fun searchResult(){
        val result = getAllSearch()
        uiState = UiState.Result(result)
    }
    fun updateUserInput(newUserInput: String){
        userInput = newUserInput
        viewModelScope.launch{
            searchResult()
        }
    }
    suspend fun getAllItems(): List<Airport> = flightSearchRepository.getAllItems()

    suspend fun getAllSearch(): List<Airport> = flightSearchRepository.getAllSearch(userInput)

}





