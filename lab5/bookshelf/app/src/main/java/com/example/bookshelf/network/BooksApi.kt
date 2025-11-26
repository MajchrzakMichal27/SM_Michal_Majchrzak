package com.example.bookshelf.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BooksApi {
    private const val BASE_URL = "https://www.googleapis.com/books/v1/"

    val retrofitService: BooksApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BooksApiService::class.java)
    }
}
