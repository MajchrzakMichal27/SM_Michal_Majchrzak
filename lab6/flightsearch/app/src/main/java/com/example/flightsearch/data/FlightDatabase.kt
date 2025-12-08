package com.example.flightsearch.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.flightsearch.model.Airport
import com.example.flightsearch.model.Route

@Database(entities = [Airport::class, Route::class], version = 1)
abstract class FlightDatabase : RoomDatabase() {
    abstract fun flightDao(): FlightDao
}
