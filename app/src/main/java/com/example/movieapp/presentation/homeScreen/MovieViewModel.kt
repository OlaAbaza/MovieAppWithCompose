package com.example.movieapp.presentation.homeScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.repository.MovieRepositoryImpl
import com.example.movieapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repositoryImpl: MovieRepositoryImpl
) : ViewModel() {
    var state by mutableStateOf(MovieState())
        private set

    fun loadNowPlayingMoviesDate() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)


            when (val result = repositoryImpl.getNowPlayingMoviesDate()) {
                is Resource.Success -> {
                    state = state.copy(
                        isLoading = false,
                        movieData = result.data,
                        error = null
                    )

                }
                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        error = result.message,
                        movieData = null
                    )
                }

            }
        }
    }
}