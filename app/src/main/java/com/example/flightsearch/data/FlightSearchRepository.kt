package com.example.flightsearch.data

interface FlightSearchRepository {
    suspend fun getSearchResult(search: String): List<Airport>
    fun getDepartureFlight(code: String): Airport
    fun getArrivalFlights(code: String): List<Airport>
    fun getAllFavorites(): List<Favorite>
    suspend fun addToFavorite(favorite: Favorite)
    suspend fun removeFromFavorite(favorite: Favorite)
    suspend fun findOneFavorite(departureCode: String, destinationCode: String): Favorite
}

class DefaultFlightSearchRepository(private val airportDao: AirportDao): FlightSearchRepository{
    override suspend fun getSearchResult(search: String): List<Airport> = airportDao.getSearchResult(search)
    override fun getDepartureFlight(code: String): Airport = airportDao.getDepartureFlight(code)
    override fun getArrivalFlights(code: String): List<Airport> = airportDao.getArrivalFlights(code)
    override fun getAllFavorites(): List<Favorite> = airportDao.getAllFavorites()
    override suspend fun addToFavorite(favorite: Favorite) = airportDao.addToFavorite(favorite)
    override suspend fun removeFromFavorite(favorite: Favorite) = airportDao.removeFromFavorite(favorite)
    override suspend fun findOneFavorite(departureCode: String, destinationCode: String): Favorite =
        airportDao.findOneFavorite(departureCode,destinationCode)
}