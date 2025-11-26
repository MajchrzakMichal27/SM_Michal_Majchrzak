package com.example.cityapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Place(
    @StringRes val nameRes: Int,
    @StringRes val shortDescRes: Int,
    @StringRes val longDescRes: Int,
    @DrawableRes val imageRes: Int
)
