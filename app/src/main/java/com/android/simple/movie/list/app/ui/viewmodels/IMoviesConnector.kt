package com.android.simple.movie.list.app.ui.viewmodels

import com.android.simple.movie.list.app.data.models.Genre
import com.android.simple.movie.list.app.data.models.Movie
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject

interface IMoviesConnector {

    /**
     * Stores the current list of genres by which the list is filtered.
     */
    val genreFilters: BehaviorSubject<List<Genre>>

    /**
     * Displays all movies in [MoviesActivity].
     */
    val onShowAllMovies: Observable<List<Movie>>

    /**
     * Indicates when user click on filters button.
     */
    val onFiltersButtonClick: PublishSubject<Unit>

    /**
     * Displays all genres as filters in [FiltersDialogFragment].
     */
    val onShowFilters: Observable<List<Pair<Genre, Boolean>>>

    /**
     * Called after the user confirms the selected filters.
     */
    fun onApplyFilters(genres: List<Genre>)
}