package com.example.flightsearch.data

import kotlinx.coroutines.flow.Flow

interface FlightSearchRepository {
    suspend fun getSearchResult(search: String): List<Airport>
    fun getDepartureFlight(code: String): Airport
    fun getArrivalFlights(code: String): List<Airport>
}

class DefaultFlightSearchRepository(private val airportDao: AirportDao): FlightSearchRepository{
    override suspend fun getSearchResult(search: String): List<Airport> = airportDao.getSearchResult(search)
    override fun getDepartureFlight(code: String): Airport = airportDao.getDepartureFlight(code)
    override fun getArrivalFlights(code: String): List<Airport> = airportDao.getArrivalFlights(code)

}