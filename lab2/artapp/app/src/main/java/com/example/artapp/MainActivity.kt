package com.example.artapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.artapp.ui.theme.ArtAppTheme
import com.example.artapp.R

data class Artwork(
    val title: String,
    val artist: String,
    val year: String,
    @DrawableRes val imageRes: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtAppScreen()
                }
            }
        }
    }
}

@Composable
fun ArtAppScreen() {
    val artworks = listOf(
        Artwork("Drzewko", "Nieznany", "nie wiem", R.drawable.art0),
        Artwork("Wioska", "Nieznany", "nie wiem", R.drawable.art1),
        Artwork("Zachód", "Nieznany", "nie wiem", R.drawable.art2)
    )

    var index by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Art Space",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        ArtworkCard(artwork = artworks[index])

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    if (index > 0) index-- else index = artworks.lastIndex
                }
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Poprzedni")
            }

            Text(
                text = "${index + 1} z ${artworks.size}",
                style = MaterialTheme.typography.bodyLarge
            )

            IconButton(
                onClick = {
                    if (index < artworks.lastIndex) index++ else index = 0
                }
            ) {
                Icon(Icons.Filled.ArrowForward, contentDescription = "Następny")
            }
        }
    }
}

@Composable
fun ArtworkCard(artwork: Artwork) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = artwork.imageRes),
                contentDescription = artwork.title,
                modifier = Modifier
                    .height(280.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = artwork.title, style = MaterialTheme.typography.titleMedium)
            Text(text = artwork.artist, style = MaterialTheme.typography.bodyMedium)
            Text(text = artwork.year, style = MaterialTheme.typography.bodySmall)
        }
    }
}
