package com.android.simple.movie.list.app.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.android.simple.movie.list.app.data.repositories.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MoviesRepository,
) : IMoviesConnector by MoviesConnector(repository), ViewModel()