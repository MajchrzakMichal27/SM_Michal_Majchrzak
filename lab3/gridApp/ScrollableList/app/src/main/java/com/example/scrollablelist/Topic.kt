package com.example.scrollablelist

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val name: Int,
    val number: Int,
    @DrawableRes val imageRes: Int
)