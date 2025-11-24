package com.example.mycityapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mycityapp.model.CityRepository
import com.example.mycityapp.model.Category
import com.example.mycityapp.model.Place
import com.example.mycityapp.ui.*
import com.example.mycityapp.ui.theme.MyCityAppTheme

class MainActivity : ComponentActivity() {

    private var selectedCategory: Category? = null
    private var selectedPlace: Place? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCityAppTheme {

                when {
                    selectedPlace != null -> PlaceDetailsScreen(selectedPlace!!)

                    selectedCategory != null -> PlacesScreen(
                        places = selectedCategory!!.places,
                        onPlaceClick = { selectedPlace = it; setContent { recreateUi() } }
                    )

                    else -> CategoriesScreen(
                        categories = CityRepository.categories,
                        onCategoryClick = { selectedCategory = it; setContent { recreateUi() } }
                    )
                }
            }
        }
    }

    private fun recreateUi() {
        setContent {
            MyCityAppTheme {
                when {
                    selectedPlace != null -> PlaceDetailsScreen(selectedPlace!!)
                    selectedCategory != null -> PlacesScreen(
                        places = selectedCategory!!.places,
                        onPlaceClick = { selectedPlace = it; setContent { recreateUi() } }
                    )
                    else -> CategoriesScreen(
                        categories = CityRepository.categories,
                        onCategoryClick = { selectedCategory = it; setContent { recreateUi() } }
                    )
                }
            }
        }
    }
}
