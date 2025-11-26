package com.example.bookshelf.model

import com.example.bookshelf.network.BooksApiService

class BookRepository(private val api: BooksApiService) {
    suspend fun getBooks(query: String): List<Book> {
        val response = api.getBooks(query)
        return response.items.map { it.volumeInfo }
    }
}
