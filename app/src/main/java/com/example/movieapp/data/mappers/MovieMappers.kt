package com.example.movieapp.data.mappers

import com.example.movieapp.data.remote.MovieDto
import com.example.movieapp.domain.MovieDetailsData

fun MovieDto.toMovieData(): List<MovieDetailsData>? {
    return results?.map { movie ->
        MovieDetailsData(
            name = movie?.original_title.toString(),
            posterImgPath = movie?.poster_path.toString(),
            overview = movie?.overview.toString(),
            genre_ids = movie?.genre_ids,
            backdrop_path = movie?.backdrop_path,
            vote_average = movie?.vote_average
        )
    }
}