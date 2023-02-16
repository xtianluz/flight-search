package com.example.flightsearch.data

import android.content.Context


interface AppContainer{
    val flightSearchRepository: FlightSearchRepository
    val userPreferencesRepository: UserPreferencesRepository
}

class DefaultAppContainer(context: Context): AppContainer{
    override val flightSearchRepository: FlightSearchRepository by lazy {
        DefaultFlightSearchRepository(AirportDatabase.getAirportDatabase(context).airportDao())
    }
    override val userPreferencesRepository: UserPreferencesRepository by lazy{
        UserPreferencesRepository(context.dataStore)
    }
}