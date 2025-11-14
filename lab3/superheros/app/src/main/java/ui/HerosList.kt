package com.example.superheros.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.superheros.model.Hero

@Composable
fun HeroesList(heroes: List<Hero>) {
    LazyColumn {
        items(heroes) { hero ->
            HeroItem(hero = hero)
        }
    }
}
