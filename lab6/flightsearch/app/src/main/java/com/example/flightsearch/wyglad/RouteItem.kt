package com.example.flightsearch.wyglad

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flightsearch.model.Route

@Composable
fun RouteItem(route: Route, onFavoriteClick: (Route) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Row(
            Modifier
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = "From: ${route.departureIata}")
                Text(text = "To: ${route.arrivalIata}")
            }
            Text(
                text = if (route.isFavorite) "★" else "☆",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.clickable { onFavoriteClick(route) }
            )
        }
    }
}
