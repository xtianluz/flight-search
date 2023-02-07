package com.example.flightsearch.data

import kotlinx.coroutines.flow.Flow

interface FlightSearchRepository {
    fun getAllSearch(search: String): Flow<List<Airport>>

    fun getAllItems(): Flow<List<Airport>>
}

class DefaultFlightSearchRepository(private val airportDao: AirportDao): FlightSearchRepository{
    override fun getAllSearch(search: String): Flow<List<Airport>> = airportDao.getAllSearch(search)

    override fun getAllItems(): Flow<List<Airport>> = airportDao.getAllItems()

}