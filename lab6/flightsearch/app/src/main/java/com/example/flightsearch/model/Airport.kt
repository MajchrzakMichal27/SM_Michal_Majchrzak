package com.example.flightsearch.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Airport(
    @PrimaryKey val iataCode: String,
    val name: String
)
