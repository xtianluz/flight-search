package com.example.flightsearch.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "airport")
data class Airport(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,

    @ColumnInfo(name = "iata_code")
    val iataCode: String,

    val passenger: Int
)