package com.example.bookshelf.model

import com.google.gson.annotations.SerializedName

data class Book(
    val title: String,
    val authors: List<String> = emptyList(),
    val description: String = "",
    @SerializedName("imageLinks") val imageLinks: ImageLinks? = null
)

data class ImageLinks(
    val thumbnail: String? = null
)
