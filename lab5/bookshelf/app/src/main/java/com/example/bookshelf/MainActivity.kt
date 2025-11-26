package com.example.bookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.bookshelf.model.BookRepository
import com.example.bookshelf.network.BooksApi
import com.example.bookshelf.look.BooksScreen
import com.example.bookshelf.look.BookViewModel
import com.example.bookshelf.look.BooksUiState
import com.example.bookshelf.ui.theme.BookshelfTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.material3.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = BookRepository(BooksApi.retrofitService)
        val viewModel = BookViewModel(repository)
        viewModel.searchBooks("android")

        setContent {
            val uiState by viewModel.uiState.collectAsState()
            BookshelfTheme {
                when (uiState) {
                    is BooksUiState.Loading -> Text("Loading...")
                    is BooksUiState.Success -> BooksScreen(books = (uiState as BooksUiState.Success).books)
                    is BooksUiState.Error -> Text("Error: ${(uiState as BooksUiState.Error).message}")
                }
            }
        }
    }
}
