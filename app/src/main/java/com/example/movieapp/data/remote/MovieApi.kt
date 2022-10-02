package com.example.movieapp.data.remote

import com.example.movieapp.data.API_KEY
import retrofit2.http.GET

interface MovieApi {
    @GET("now_playing?language=en-US&page=1&api_key=$API_KEY")
    suspend fun getNowPlayingMovies(): MovieDto
}