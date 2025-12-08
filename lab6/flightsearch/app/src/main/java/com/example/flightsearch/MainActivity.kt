package com.example.flightsearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.example.flightsearch.data.FlightDatabase
import com.example.flightsearch.model.FlightRepository
import com.example.flightsearch.wyglad.FlightViewModel
import com.example.flightsearch.wyglad.FlightsScreen
import com.example.flightsearch.wyglad.FlightsUiState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            FlightDatabase::class.java,
            "flight-db"
        ).build()

        val repository = FlightRepository(db)
        val viewModel = FlightViewModel(repository)

        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val uiState by viewModel.uiState.collectAsState()

                    when (uiState) {
                        is FlightsUiState.Loading -> {
                            Text(text = "Loading...")
                        }
                        is FlightsUiState.Error -> {
                            Text(text = "Error occurred")
                        }
                        is FlightsUiState.Success -> {
                            FlightsScreen(
                                routes = (uiState as FlightsUiState.Success).routes,
                                onFavoriteClick = { viewModel.toggleFavorite(it) }
                            )
                        }
                    }
                }
            }
        }
    }
}
