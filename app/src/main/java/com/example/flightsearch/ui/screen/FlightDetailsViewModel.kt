package com.example.flightsearch.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.flightsearch.data.Favorite
import com.example.flightsearch.data.FlightSearchRepository

class FlightDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val flightSearchRepository: FlightSearchRepository
) : ViewModel() {
    private val itemCode: String = savedStateHandle[FlightDetailDestination.itemIdArg] ?: ""

    val selectedDeparture = flightSearchRepository.getDepartureFlight(itemCode)
    val selectedArrivals = flightSearchRepository.getArrivalFlights(itemCode)
    var starTint: Color by mutableStateOf(Color.LightGray)
    private var foundFavorite: Favorite by mutableStateOf(
        Favorite(
            id = 0,
            departure_code = "FOO",
            destination_code = "FOO"
        )
    )
    private suspend fun addToFavorite(departureCode: String, destinationCode: String){
        flightSearchRepository.addToFavorite(
            favorite = Favorite(
                id = 0,//set to 0 so autogenerate will decide
                departure_code = departureCode,
                destination_code = destinationCode
            )
        )
    }
    private suspend fun removeFromFavorite(favorite: Favorite){
        flightSearchRepository.removeFromFavorite(
                favorite = favorite
        )
    }
    private suspend fun findOneFavorite(departureCode: String, destinationCode: String) {
        foundFavorite = flightSearchRepository.findOneFavorite(
            departureCode = departureCode,
            destinationCode = destinationCode
        )
    }
    suspend fun addRemoveToFavorite(
        departureCode: String,
        destinationCode: String,
        id: Int
    ) {
        findOneFavorite(
            departureCode = departureCode,
            destinationCode = destinationCode
        )
        //ignore if statement warning for giving result always false
        if(foundFavorite == null){
            addToFavorite(
                departureCode = departureCode,
                destinationCode = destinationCode
            )
            starTint = Color.Cyan
        }else{
            removeFromFavorite(
                favorite = foundFavorite
            )
            starTint = Color.LightGray
        }
    }

}
