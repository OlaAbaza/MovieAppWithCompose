package com.example.movieapp.presentation.homeScreen

import com.example.movieapp.domain.MovieDetailsData

data class MovieState(
    val movieData: List<MovieDetailsData>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)