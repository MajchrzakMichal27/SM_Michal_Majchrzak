package com.example.flightsearch.model

import com.example.flightsearch.data.FlightDatabase
import kotlinx.coroutines.flow.Flow

class FlightRepository(private val db: FlightDatabase) {

    fun searchAirports(query: String): Flow<List<Airport>> {
        return db.flightDao().searchAirports("%$query%")
    }

    fun getRoutesFromAirport(departure: String): Flow<List<Route>> {
        return db.flightDao().getRoutesFromAirport(departure)
    }

    fun getFavoriteRoutes(): Flow<List<Route>> {
        return db.flightDao().getFavoriteRoutes()
    }

    suspend fun updateRoute(route: Route) {
        db.flightDao().updateRoute(route)
    }

    suspend fun addAirport(airport: Airport) {
        db.flightDao().insertAirport(airport)
    }

    suspend fun addRoute(route: Route) {
        db.flightDao().insertRoute(route)
    }
}
