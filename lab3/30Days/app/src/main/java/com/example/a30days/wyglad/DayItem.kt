package com.example.a30days.wyglad

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.a30days.model.Day

@Composable
fun DayItem(day: Day) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = stringResource(day.nameRes), style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = stringResource(day.shortDescriptionRes), style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = painterResource(day.imageRes),
                contentDescription = stringResource(day.nameRes),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = stringResource(day.longDescriptionRes), style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
