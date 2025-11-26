package com.example.cityapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.cityapp.model.CityRepository
import com.example.cityapp.model.Category
import com.example.cityapp.model.Place
import com.example.cityapp.ui.*
import com.example.cityapp.ui.theme.CityappTheme

class MainActivity : ComponentActivity() {

    private var selectedCategory: Category? = null
    private var selectedPlace: Place? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CityappTheme {

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
            CityappTheme {
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
