package com.example.cityapp.model

import com.example.cityapp.R

object CityRepository {

    val categories = listOf(
        Category(
            nameRes = R.string.cat_parks,
            imageRes = R.drawable.park,
            places = listOf(
                Place(
                    nameRes = R.string.park_1,
                    shortDescRes = R.string.park_1_short,
                    longDescRes = R.string.park_1_long,
                    imageRes = R.drawable.park1
                ),
                Place(
                    nameRes = R.string.park_2,
                    shortDescRes = R.string.park_2_short,
                    longDescRes = R.string.park_2_long,
                    imageRes = R.drawable.park2
                )
            )
        ),

        Category(
            nameRes = R.string.cat_food,
            imageRes = R.drawable.food,
            places = listOf(
                Place(
                    nameRes = R.string.food_1,
                    shortDescRes = R.string.food_1_short,
                    longDescRes = R.string.food_1_long,
                    imageRes = R.drawable.food1
                )
            )
        )
    )
}
