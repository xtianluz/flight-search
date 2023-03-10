package com.example.flightsearch.ui


import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearch.FlightApplication
import com.example.flightsearch.ui.screen.FlightDetailsViewModel
import com.example.flightsearch.ui.screen.FlightSearchViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            FlightSearchViewModel(
                flightApplication().container.flightSearchRepository,
                flightApplication().container.userPreferencesRepository
            )
        }
        initializer {
            FlightDetailsViewModel(
                this.createSavedStateHandle(),
                flightApplication().container.flightSearchRepository
            )
        }
    }
}

fun CreationExtras.flightApplication(): FlightApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FlightApplication)