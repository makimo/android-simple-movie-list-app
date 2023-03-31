package com.android.simple.movie.list.app.ui.movie_list

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.simple.movie.list.app.R
import com.android.simple.movie.list.app.data.models.Genre
import com.android.simple.movie.list.app.data.models.Movie
import com.android.simple.movie.list.app.ui.viewmodels.MoviesViewModel
import com.android.simple.movie.list.app.uitls.click
import com.android.simple.movie.list.app.uitls.connect
import com.android.simple.movie.list.app.uitls.hideSystemNavigationBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_movies.*

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {

    private val moviesViewModel: MoviesViewModel by viewModels()

    private lateinit var moviesAdapter: MovieAdapter
    private val filtersDialog = FiltersDialogFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        hideSystemNavigationBar()
        setupAdapter()
        setupObservers()
        setupListeners()
    }

    private fun setupListeners() {
        txt_filters.click {
            moviesViewModel.onFiltersButtonClick.onNext(Unit)
        }
    }

    private fun setupObservers() {
        connect(this, moviesViewModel.onShowAllMovies, ::onShowAllMovies)
        connect(this, moviesViewModel.onShowFilters, ::onShowFilters)

        connect(this, filtersDialog.onApplyFilters, moviesViewModel::onApplyFilters)
    }

    private fun onShowFilters(value: List<Pair<Genre, Boolean>>) {
        filtersDialog.setFilters(value)
        filtersDialog.show(supportFragmentManager, "filters")
    }

    private fun onShowAllMovies(movies: List<Movie>) {
        moviesAdapter.setData(movies)
    }

    private fun setupAdapter() {
        moviesAdapter = MovieAdapter()
        val lm = LinearLayoutManager(this)
        recycler_movies.apply {
            layoutManager = lm
            itemAnimator = null
            adapter = moviesAdapter.apply {
                setHasStableIds(true)
            }
        }
    }
}