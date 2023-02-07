package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AirportDao {

    @Query("""
          SELECT *
          FROM airport
          WHERE name = :search
          OR iata_code LIKE '%:search%'
    """
    )
    fun getAllSearch(search: String): Flow<List<Airport>>

    @Query("""
        SELECT *
        FROM airport
    """)
    fun getAllItems(): Flow<List<Airport>>

}

