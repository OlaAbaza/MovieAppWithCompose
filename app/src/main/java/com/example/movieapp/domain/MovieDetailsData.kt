package com.example.movieapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class MovieDetailsData(
    val name: String,
    val backdrop_path: String? = null,
    val genre_ids: List<Int?>? = null,
    val overview: String? = null,
    val vote_average: Double? = null,
    val posterImgPath: String? = null
) : Parcelable