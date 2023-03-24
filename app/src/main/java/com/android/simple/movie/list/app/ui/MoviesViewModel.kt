package com.android.simple.movie.list.app.ui

import androidx.lifecycle.ViewModel
import com.android.simple.movie.list.app.data.models.Movie
import com.android.simple.movie.list.app.data.repositories.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.Observables
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    val allMovies = repository.allMovies

    val allGenres = repository.allGenres
}