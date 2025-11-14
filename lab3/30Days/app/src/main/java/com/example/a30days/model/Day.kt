package com.example.a30days.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Day(
    @StringRes val nameRes: Int,
    @StringRes val shortDescriptionRes: Int,
    @StringRes val longDescriptionRes: Int,
    @DrawableRes val imageRes: Int
)
