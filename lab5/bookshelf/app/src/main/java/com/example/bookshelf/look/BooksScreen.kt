package com.example.bookshelf.look

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookshelf.model.Book

@Composable
fun BooksScreen(books: List<Book>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(8.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        items(books) { book ->
            BookItem(book = book)
        }
    }
}
