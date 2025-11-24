package com.example.mycityapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Category(
    @StringRes val nameRes: Int,
    @DrawableRes val imageRes: Int,
    val places: List<Place>
)
