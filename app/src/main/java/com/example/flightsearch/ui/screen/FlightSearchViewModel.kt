package com.example.flightsearch.ui.screen

import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
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
    savedStateHandle: SavedStateHandle,
    private val flightSearchRepository: FlightSearchRepository,

): ViewModel(){

//    private val itemId: Int = checkNotNull(savedStateHandle[FlightDetailDestination.itemIdArg])

    var userInput: String by mutableStateOf("")
        private set

    var uiState: UiState by mutableStateOf(UiState.DefaultState)
        private set

    private var flightList: List<Airport> by mutableStateOf(emptyList())

    private suspend fun getResult(){
        flightList = getAllItems()
        uiState = UiState.Result(flightList)
    }

    private suspend fun searchResult(){
        flightList = getAllSearch()
        uiState = UiState.Result(flightList)
    }
    fun updateUserInput(newUserInput: String){
        userInput = newUserInput
        if(userInput.isNotBlank()){
            viewModelScope.launch{
                searchResult()
            }
        } else{
            uiState = UiState.DefaultState
        }

    }
    private suspend fun getAllItems(): List<Airport> = flightSearchRepository.getAllItems()
    private suspend fun getAllSearch(): List<Airport> = flightSearchRepository.getAllSearch(userInput)

}





