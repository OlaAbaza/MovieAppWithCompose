package com.example.movieapp.domain.repository

import com.example.movieapp.domain.MovieDetailsData
import com.example.movieapp.domain.util.Resource

interface MovieRepository {
    suspend fun getNowPlayingMoviesDate(): Resource<List<MovieDetailsData>>
}