package com.example.flightsearch.data

interface FlightSearchRepository {
    suspend fun getSearchResult(search: String): List<Airport>
    fun getDepartureFlight(code: String): Airport
    fun getArrivalFlights(code: String): List<Airport>
    fun getAllFavorites(): List<Favorite>
    suspend fun addToFavorite(flight: Favorite)
    suspend fun removeFromFavorite(flight: Favorite)
    suspend fun isExistingFavorite(departureCode: String, destinationCode: String): Favorite
}

class DefaultFlightSearchRepository(private val airportDao: AirportDao): FlightSearchRepository{
    override suspend fun getSearchResult(search: String): List<Airport> = airportDao.getSearchResult(search)
    override fun getDepartureFlight(code: String): Airport = airportDao.getDepartureFlight(code)
    override fun getArrivalFlights(code: String): List<Airport> = airportDao.getArrivalFlights(code)
    override fun getAllFavorites(): List<Favorite> = airportDao.getAllFavorites()
    override suspend fun addToFavorite(flight: Favorite) = airportDao.addToFavorite(flight)
    override suspend fun removeFromFavorite(flight: Favorite) = airportDao.removeFromFavorite(flight)
    override suspend fun isExistingFavorite(departureCode: String, destinationCode: String): Favorite =
        airportDao.isExistingFavorite(departureCode,destinationCode)
}