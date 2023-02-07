package com.example.flightsearch.data

import android.content.Context

interface AppContainer{
    val flightSearchRepository: FlightSearchRepository
}

class DefaultAppContainer(context: Context):AppContainer{
    override val flightSearchRepository: FlightSearchRepository by lazy {
        DefaultFlightSearchRepository(AirportDatabase.getAirportDatabase(context).airportDao())
    }
}