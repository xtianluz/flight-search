package com.example.flightsearch.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightsearch.data.Favorite
import com.example.flightsearch.data.FlightSearchRepository
import kotlinx.coroutines.launch

class FlightDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val flightSearchRepository: FlightSearchRepository
) : ViewModel() {
    private val itemCode: String = savedStateHandle[FlightDetailDestination.itemIdArg] ?: ""

    val selectedDeparture = flightSearchRepository.getDepartureFlight(itemCode)
    val selectedArrivals = flightSearchRepository.getArrivalFlights(itemCode)
    var isFavorite: Boolean by mutableStateOf(false)

    private var queryFavorite: Favorite by mutableStateOf(
        Favorite(
            0,
            "empty",
            "empty"
        )
    )
    var isExisting: Boolean by mutableStateOf(false)

    //check if the selected flight already exist in the database
    //if existing then can proceed to remove from favorite
    private suspend fun checkIfExisting(departureCode: String, destinationCode: String) {
        queryFavorite =
        try {
            flightSearchRepository.isExistingFavorite(
                destinationCode = destinationCode,
                departureCode = departureCode
            )
        } catch (_error: Error){
            throw Exception(_error)
        }
    }

    suspend fun addRemoveToFavorite(
        departureCode: String,
        destinationCode: String,
        id: Int
    ) {
        viewModelScope.launch {
            checkIfExisting(
                departureCode = departureCode,
                destinationCode = destinationCode
            )
            println("$queryFavorite")
            if(queryFavorite != null){
            isExisting = queryFavorite.departureCode == departureCode && queryFavorite.destinationCode == destinationCode
                if(isExisting){
                    println("Favorite already exist")
                }else{
                    println("Added to favorite")
                }
            }else{
                println("$queryFavorite")
            }
        }
    }
}
