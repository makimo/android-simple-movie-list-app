package com.android.simple.movie.list.app.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.android.simple.movie.list.app.data.repositories.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    val allMovies = repository.allMovies

    val allGenres = repository.allGenres
}