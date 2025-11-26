package com.example.bookshelf.look

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelf.model.Book
import com.example.bookshelf.model.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class BooksUiState {
    object Loading : BooksUiState()
    data class Success(val books: List<Book>) : BooksUiState()
    data class Error(val message: String) : BooksUiState()
}

class BookViewModel(private val repository: BookRepository): ViewModel() {

    private val _uiState = MutableStateFlow<BooksUiState>(BooksUiState.Loading)
    val uiState: StateFlow<BooksUiState> = _uiState

    fun searchBooks(query: String) {
        viewModelScope.launch {
            _uiState.value = BooksUiState.Loading
            try {
                val books = repository.getBooks(query)
                _uiState.value = BooksUiState.Success(books)
            } catch (e: Exception) {
                _uiState.value = BooksUiState.Error(e.localizedMessage ?: "Error")
            }
        }
    }
}
