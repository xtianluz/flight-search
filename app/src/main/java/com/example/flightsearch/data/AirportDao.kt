package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

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
    fun getDepartureFlight(iata_code: String): Flow<Airport>
    @Query("SELECT * FROM airport WHERE iata_code != :iata_code")
    fun getArrivalFlights(iata_code: String): Flow<List<Airport>>
}