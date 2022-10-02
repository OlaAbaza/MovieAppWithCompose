package com.example.movieapp

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieapp.domain.MovieDetailsData
import com.example.movieapp.presentation.homeScreen.MovieState
import com.example.movieapp.presentation.homeScreen.HomeScreen
import com.example.movieapp.presentation.movieDetails.MovieDetailsScreen
import com.example.movieapp.presentation.util.Screen
import com.example.movieapp.ui.theme.LightGray

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    state: MovieState
) {

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(
                navController = navController,
                modifier = Modifier
                    .background(LightGray),
                state = state
            )
        }
        composable(
            route = Screen.MovieDetailsScreen.route
        ) {
            val movieData =
                navController.previousBackStackEntry?.savedStateHandle?.get<MovieDetailsData>("movieData")
            Log.d("movieData", movieData?.overview.toString())
            if (movieData != null) {
                MovieDetailsScreen(
                    modifier = Modifier.background(LightGray),
                    navController = navController,
                    movieDetails = movieData
                )
            }
        }
    }

}