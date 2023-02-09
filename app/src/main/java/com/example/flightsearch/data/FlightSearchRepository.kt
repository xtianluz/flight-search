package com.example.flightsearch.data

import kotlinx.coroutines.flow.Flow

interface FlightSearchRepository {
    suspend fun getAllSearch(search: String): List<Airport>

    suspend fun getAllItems(): List<Airport>
    suspend fun getItem(id: Int): Airport
}

class DefaultFlightSearchRepository(private val airportDao: AirportDao): FlightSearchRepository{
    override suspend fun getAllSearch(search: String): List<Airport> = airportDao.getAllSearch(search)

    override suspend fun getAllItems(): List<Airport> = airportDao.getAllItems()
    override suspend fun getItem(id: Int): Airport = airportDao.getItem(id)

}