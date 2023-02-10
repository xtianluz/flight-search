package com.example.flightsearch.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightsearch.data.FlightSearchRepository
import kotlinx.coroutines.launch

class FlightDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val flightSearchRepository: FlightSearchRepository
) : ViewModel(){
     val itemCode: String = savedStateHandle[FlightDetailDestination.itemIdArg] ?: "WAW"
//    private val itemCode: String = checkNotNull(savedStateHandle[FlightDetailDestination.itemIdArg])
//    var flightInfo by mutableStateOf("")
//        private set
//
//    fun getCode() {
//        viewModelScope.launch {
//            flightSearchRepository.getCode(itemCode).collect{
//                flightInfo = it.iata_code
//            }
//        }
//    }

}