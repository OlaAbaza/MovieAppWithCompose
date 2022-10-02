package com.example.movieapp.presentation.util

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object MovieDetailsScreen : Screen("movie_details_screen")
}