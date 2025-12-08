package com.example.flightsearch.wyglad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightsearch.model.Airport
import com.example.flightsearch.model.FlightRepository
import com.example.flightsearch.model.Route
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

// Sealed class stanu UI
sealed class FlightsUiState {
    object Loading : FlightsUiState()
    data class Success(val routes: List<Route>) : FlightsUiState()
    data class Error(val message: String) : FlightsUiState()
}

class FlightViewModel(private val repository: FlightRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<FlightsUiState>(FlightsUiState.Loading)
    val uiState: StateFlow<FlightsUiState> = _uiState

    init {
        viewModelScope.launch {
            try {
                // Dodaj przykładowe dane
                repository.addAirport(Airport("WAW", "Warsaw"))
                repository.addAirport(Airport("KRK", "Krakow"))
                repository.addAirport(Airport("GDN", "Gdansk"))

                repository.addRoute(Route(departureIata = "WAW", arrivalIata = "KRK"))
                repository.addRoute(Route(departureIata = "WAW", arrivalIata = "GDN"))

                searchRoutes("WAW")
            } catch (e: Exception) {
                _uiState.value = FlightsUiState.Error(e.localizedMessage ?: "Error")
            }
        }
    }

    fun searchRoutes(query: String) {
        viewModelScope.launch {
            repository.searchAirports(query)
                .flatMapLatest { airports ->
                    if (airports.isNotEmpty()) {
                        repository.getRoutesFromAirport(airports.first().iataCode)
                    } else {
                        flowOf(emptyList())
                    }
                }
                .catch { e -> _uiState.value = FlightsUiState.Error(e.localizedMessage ?: "Error") }
                .collect { routes -> _uiState.value = FlightsUiState.Success(routes) }
        }
    }

    fun toggleFavorite(route: Route) {
        viewModelScope.launch {
            route.isFavorite = !route.isFavorite
            repository.updateRoute(route)
        }
    }
}
