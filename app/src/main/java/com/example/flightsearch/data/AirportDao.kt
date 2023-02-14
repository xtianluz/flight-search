package com.example.flightsearch.data

import androidx.room.*

@Dao
interface AirportDao {
    @Query(
        """
          SELECT *
          FROM airport
          WHERE name LIKE '%'|| :search ||'%'
          OR iata_code LIKE '%'|| :search ||'%'
          """
    )
    suspend fun getSearchResult(search: String): List<Airport>
    @Query("SELECT * FROM airport WHERE iata_code = :iata_code")
    fun getDepartureFlight(iata_code: String): Airport
    @Query("SELECT * FROM airport WHERE iata_code != :iata_code")
    fun getArrivalFlights(iata_code: String): List<Airport>
    @Query("SELECT * FROM favorite")
    fun getAllFavorites(): List<Favorite>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorite(flight: Favorite)
    @Delete
    suspend fun removeFromFavorite(flight: Favorite)
    @Query("SELECT * FROM favorite WHERE departure_code = :departureCode AND destination_code = :destinationCode")
    suspend fun isExistingFavorite(departureCode: String, destinationCode: String): Favorite
}