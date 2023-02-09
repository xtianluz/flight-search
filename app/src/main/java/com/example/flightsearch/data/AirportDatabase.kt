package com.example.flightsearch.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Airport::class],
    version = 4,
    exportSchema = false
    )
abstract class AirportDatabase: RoomDatabase() {
    abstract fun airportDao(): AirportDao

    companion object{
        @Volatile
        private var INSTANCE: AirportDatabase? = null

        fun getAirportDatabase(context: Context): AirportDatabase {
            return INSTANCE?: synchronized(this){
                Room.databaseBuilder(context, AirportDatabase::class.java, "flight")
                    .createFromAsset("flight.db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}