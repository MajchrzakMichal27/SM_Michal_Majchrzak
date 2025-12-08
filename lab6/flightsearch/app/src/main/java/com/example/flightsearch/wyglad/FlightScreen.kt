package com.example.flightsearch.wyglad

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.flightsearch.model.Route

@Composable
fun FlightsScreen(routes: List<Route>, onFavoriteClick: (Route) -> Unit) {
    LazyColumn {
        items(routes) { route ->
            RouteItem(route = route, onFavoriteClick = onFavoriteClick)
        }
    }
}
