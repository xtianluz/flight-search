package com.example.flightsearch.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.FlightSearchRepository
import kotlinx.coroutines.launch

data class FlightsOptions(
    val departureFlight: List<Airport> = emptyList(),
    val arrivalFlights: List<Airport> = emptyList()
)
class FlightDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val flightSearchRepository: FlightSearchRepository
) : ViewModel(){
    val itemCode: String = savedStateHandle[FlightDetailDestination.itemIdArg] ?: ""

    val selectedDeparture = flightSearchRepository.getDepartureFlight(itemCode)
    val selectedArrivals = flightSearchRepository.getArrivalFlights(itemCode)

    var flightOptions by mutableStateOf(FlightsOptions())
        private set



}