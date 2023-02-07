package com.example.flightsearch.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "airport")
data class Airport(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @NonNull
    @ColumnInfo(name = "name")
    val name: String,

    @NonNull
    @ColumnInfo(name = "iata_code")
    val iataCode: String,

    @NonNull
    @ColumnInfo(name = "passengers")
    val passengers: Int
)