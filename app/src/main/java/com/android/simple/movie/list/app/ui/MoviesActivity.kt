package com.android.simple.movie.list.app.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.android.simple.movie.list.app.R
import com.android.simple.movie.list.app.data.models.Genre
import com.android.simple.movie.list.app.data.models.Movie
import com.android.simple.movie.list.app.uitls.connect
import com.android.simple.movie.list.app.uitls.hideSystemNavigationBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {

    private val moviesViewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        hideSystemNavigationBar()
        setupObservers()
    }

    private fun setupObservers() {
        connect(this, moviesViewModel.allGenres, ::onAllGenres)
        connect(this, moviesViewModel.allMovies, ::onAllMovies)
    }

    private fun onAllGenres(genres: List<Genre>) {
        // TODO
    }

    private fun onAllMovies(movies: List<Movie>) {
        // TODO
    }
}