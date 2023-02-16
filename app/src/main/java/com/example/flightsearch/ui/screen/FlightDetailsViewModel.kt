package com.example.flightsearch.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.flightsearch.data.Favorite
import com.example.flightsearch.data.FlightSearchRepository
import com.example.flightsearch.data.LocalData

class FlightDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val flightSearchRepository: FlightSearchRepository
) : ViewModel() {
    private val itemCode: String = savedStateHandle[FlightDetailDestination.itemIdArg] ?: ""

    val selectedDeparture = flightSearchRepository.getDepartureFlight(itemCode)
    val selectedArrivals = flightSearchRepository.getArrivalFlights(itemCode)
    var alreadyExist: Boolean by mutableStateOf(false)
    private var foundFavorite: Favorite by mutableStateOf(LocalData.singleFavorite)
    private suspend fun addToFavorite(departureCode: String, destinationCode: String){
        flightSearchRepository.addToFavorite(
            favorite = Favorite(
                id = 0,//set to 0 so autogenerate will decide
                departure_code = departureCode,
                destination_code = destinationCode
            )
        )
    }
    private suspend fun findOneFavorite(departureCode: String, destinationCode: String) {
        foundFavorite = flightSearchRepository.findOneFavorite(
            departureCode = departureCode,
            destinationCode = destinationCode
        )
    }
    suspend fun addToFavoriteWithQuery(
        departureCode: String,
        destinationCode: String,
    ) {
        findOneFavorite(
            departureCode = departureCode,
            destinationCode = destinationCode
        )
        //Just ignore warning, condition always true because of initial value, until fetching data from database
        //do not lift assignment out of if
        if(foundFavorite == null){
            addToFavorite(
                departureCode = departureCode,
                destinationCode = destinationCode
            )
            alreadyExist = false
        }else{
            alreadyExist = true
        }
    }
}
