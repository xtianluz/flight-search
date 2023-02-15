package com.example.flightsearch.data

import androidx.room.*
import androidx.room.Insert

@Dao
interface AirportDao {
    @Insert(entity = Favorite::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToFavorite(favorite: Favorite)
    @Delete
    suspend fun removeFromFavorite(favorite: Favorite)
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
    @Query("SELECT * FROM favorite WHERE departure_code = :departure_code AND destination_code = :destination_code")
    suspend fun findOneFavorite(departure_code: String, destination_code: String): Favorite
}