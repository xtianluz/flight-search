package com.example.flightsearch.ui.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.FlightSearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow

class FlightDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val flightSearchRepository: FlightSearchRepository
) : ViewModel(){
     val itemCode: String = savedStateHandle[FlightDetailDestination.itemIdArg] ?: ""

    val selectedDeparture = flightSearchRepository.getDepartureFlight(itemCode)
    val selectedArrivals = flightSearchRepository.getArrivalFlights(itemCode)

}

data class FlightDetailsUiState(
    val selectedDeparture: List<Airport> = listOf()
)