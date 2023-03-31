package com.android.simple.movie.list.app.ui

import androidx.lifecycle.ViewModel
import com.android.simple.movie.list.app.data.models.Genre
import com.android.simple.movie.list.app.data.repositories.MoviesRepository
import com.android.simple.movie.list.app.uitls.defaultBehavior
import com.android.simple.movie.list.app.uitls.publishSubject
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.Observables
import io.reactivex.rxjava3.kotlin.withLatestFrom
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MoviesRepository,
) : ViewModel() {

    val filters = defaultBehavior<List<Genre>>(emptyList())

    val onShowAllMovies =
        Observables.combineLatest(repository.allMovies, filters).map { (movies, filters) ->
            movies.filter { movie ->
                if (filters.isNotEmpty()) {
                    filters.any { genre ->
                        movie.genres.any {
                            it.uuid == genre.uuid
                        }
                    }
                } else {
                    true
                }
            }
        }

    val onFiltersButtonClick = publishSubject<Unit>()

    val onShowFilters =
        onFiltersButtonClick
            .withLatestFrom(repository.allGenres, filters)
            .map { (_, genres, filters) ->
                genres.map {
                    Pair(it, filters.contains(it))
                }
            }
            .filter { it.isNotEmpty() }

    fun onApplyFilters(genres: List<Genre>) {
        filters.onNext(genres)
    }
}