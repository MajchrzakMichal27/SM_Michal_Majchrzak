package com.example.flightsearch.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Route(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val departureIata: String,
    val arrivalIata: String,
    var isFavorite: Boolean = false
)
