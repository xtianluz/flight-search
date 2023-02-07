package com.example.flightsearch.ui.screen

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.DefaultFlightSearchRepository
import com.example.flightsearch.data.FlightSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class FlightSearchViewModel(
    private val flightSearchRepository: FlightSearchRepository
): ViewModel(){



    fun getAllItems(): Flow<List<Airport>> = flightSearchRepository.getAllItems()

    fun getAllSearch(): Flow<List<Airport>> = flightSearchRepository.getAllSearch("test")

}