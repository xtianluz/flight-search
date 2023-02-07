package com.example.flightsearch.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "airport")
data class Airport(
    @PrimaryKey()
    val id: Int,
    val name: String,
    @ColumnInfo(name = "iata_code")
    val iataCode: String,
    val passenger: Int
)

data class Flights(
    val arrivalCode: String,
    val arrivalName: String,
    val departureName: String,
    val departureCode: String,
)

data class Favourites(
    val departureCode: String,
    val arrivalCode: String,
)