package com.android.simple.movie.list.app.data.datasources

import com.android.simple.movie.list.app.data.models.Genre
import com.android.simple.movie.list.app.data.models.Movie
import com.android.simple.movie.list.app.data.models.MovieResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

interface MoviesDataSource {

    val rawMovies: BehaviorSubject<List<MovieResponse>>

    val allMovies: Observable<List<Movie>>

    val allGenres: BehaviorSubject<List<Genre>>
}