package com.example.superheros.model

import com.example.superheros.R

object HeroesRepository {

    val heroes = listOf(
        Hero(
            nameRes = R.string.hero_bohater1,
            descriptionRes = R.string.desc_bohater1,
            imageRes = R.drawable.android_superhero1
        ),
        Hero(
            nameRes = R.string.hero_bohater2,
            descriptionRes = R.string.desc_bohater2,
            imageRes = R.drawable.android_superhero2
        ),
        Hero(
            nameRes = R.string.hero_bohater3,
            descriptionRes = R.string.desc_bohater3,
            imageRes = R.drawable.android_superhero3
        ),
        Hero(
            nameRes = R.string.hero_bohater4,
            descriptionRes = R.string.desc_bohater4,
            imageRes = R.drawable.android_superhero4
        ),
        Hero(
            nameRes = R.string.hero_bohater5,
            descriptionRes = R.string.desc_bohater5,
            imageRes = R.drawable.android_superhero5
        ),
        Hero(
            nameRes = R.string.hero_bohater6,
            descriptionRes = R.string.desc_bohater6,
            imageRes = R.drawable.android_superhero6
        )
    )
}
