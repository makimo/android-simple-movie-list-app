package com.android.simple.movie.list.app.data.datasources

import com.android.simple.movie.list.app.data.models.Genre
import com.android.simple.movie.list.app.data.models.Movie
import io.reactivex.rxjava3.subjects.BehaviorSubject

interface MoviesDataSource {

    val allMovies: BehaviorSubject<List<Movie>>

    val allGenres: BehaviorSubject<List<Genre>>
}