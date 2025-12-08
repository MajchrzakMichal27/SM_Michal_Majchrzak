package com.example.flightsearch.data

import androidx.room.*
import com.example.flightsearch.model.Airport
import com.example.flightsearch.model.Route
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightDao {

    @Query("SELECT * FROM Airport WHERE name LIKE :query OR iataCode LIKE :query")
    fun searchAirports(query: String): Flow<List<Airport>>

    @Query("SELECT * FROM Route WHERE departureIata = :departure")
    fun getRoutesFromAirport(departure: String): Flow<List<Route>>

    @Query("SELECT * FROM Route WHERE isFavorite = 1")
    fun getFavoriteRoutes(): Flow<List<Route>>

    @Update
    suspend fun updateRoute(route: Route)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAirport(airport: Airport)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoute(route: Route)
}
