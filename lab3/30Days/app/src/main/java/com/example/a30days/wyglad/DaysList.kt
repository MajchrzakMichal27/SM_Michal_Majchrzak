package com.example.a30days.wyglad

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.a30days.model.Day

@Composable
fun DaysList(days: List<Day>) {
    LazyColumn {
        items(days) { day ->
            DayItem(day = day)
        }
    }
}
