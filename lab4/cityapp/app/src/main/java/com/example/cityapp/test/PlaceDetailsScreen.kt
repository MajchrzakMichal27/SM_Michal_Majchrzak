package com.example.cityapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cityapp.model.Place

@Composable
fun PlaceDetailsScreen(place: Place) {
    Column(modifier = Modifier.padding(16.dp)) {

        Image(
            painter = painterResource(id = place.imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = stringResource(id = place.nameRes))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = stringResource(id = place.longDescRes))
    }
}
