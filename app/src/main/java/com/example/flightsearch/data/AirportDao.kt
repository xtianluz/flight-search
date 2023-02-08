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
    suspend fun getAllSearch(search: String): List<Airport>

    @Query("SELECT * FROM airport")
    suspend fun getAllItems(): List<Airport>

}

//'%'|| :search ||'%'