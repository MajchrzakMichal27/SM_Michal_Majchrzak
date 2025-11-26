package com.example.bookshelf.model

data class BookResponse(
    val items: List<BookItem> = emptyList()
)

data class BookItem(
    val volumeInfo: Book
)
