package com.android.simple.movie.list.app.ui.viewmodels

import com.android.simple.movie.list.app.data.models.Genre
import com.android.simple.movie.list.app.data.models.Movie
import com.android.simple.movie.list.app.data.repositories.MoviesRepository
import com.android.simple.movie.list.app.uitls.defaultBehavior
import com.android.simple.movie.list.app.uitls.publishSubject
import io.reactivex.rxjava3.kotlin.Observables
import io.reactivex.rxjava3.kotlin.withLatestFrom

class MoviesConnector(private val repository: MoviesRepository) : IMoviesConnector {

    override val genreFilters = defaultBehavior<List<Genre>>(emptyList())

    override val onShowAllMovies =
        Observables.combineLatest(repository.allMovies, genreFilters).map { (movies, genres) ->
            filterMoviesByGenre(movies, genres)
        }

    override val onFiltersButtonClick = publishSubject<Unit>()

    override val onShowFilters =
        onFiltersButtonClick.withLatestFrom(repository.allGenres, genreFilters)
            .map { (_, genres, filters) ->
                genres.map {
                    Pair(it, filters.contains(it))
                }
            }.filter { it.isNotEmpty() }

    override fun onApplyFilters(genres: List<Genre>) = genreFilters.onNext(genres)

    private fun filterMoviesByGenre(movies: List<Movie>, genres: List<Genre>) =
        movies.filter { movie ->
            if (genres.isNotEmpty()) {
                genres.any { genre ->
                    movie.genres.any { it.uuid == genre.uuid }
                }
            } else {
                true
            }
        }
}