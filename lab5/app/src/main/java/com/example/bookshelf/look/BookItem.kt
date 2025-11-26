package com.example.bookshelf.look

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.bookshelf.model.Book

@Composable
fun BookItem(book: Book) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { expanded = !expanded }
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(text = book.title)
            Text(text = "Autor: ${book.authors.joinToString()}")
            Spacer(modifier = Modifier.height(8.dp))
            if (expanded) {
                Text(text = book.description)
            }
            Spacer(modifier = Modifier.height(8.dp))
            book.imageLinks?.thumbnail?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = book.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}
