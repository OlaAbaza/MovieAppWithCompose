package com.example.movieapp.data.repository

import com.example.movieapp.data.mappers.toMovieData
import com.example.movieapp.data.remote.MovieApi
import com.example.movieapp.domain.MovieDetailsData
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.domain.util.Resource
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRepository {
    override suspend fun getNowPlayingMoviesDate(): Resource<List<MovieDetailsData>> {
        return try {
            Resource.Success((movieApi.getNowPlayingMovies().toMovieData()))

        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")

        }
    }
}