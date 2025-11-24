package com.example.mycityapp.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.mycityapp.model.Place

@Composable
fun PlacesScreen(
    places: List<Place>,
    onPlaceClick: (Place) -> Unit
) {
    LazyColumn {
        items(places) { place ->
            PlaceItem(place = place, onClick = { onPlaceClick(place) })
        }
    }
}
