package com.android.simple.movie.list.app.data.repositories

import com.android.simple.movie.list.app.data.datasources.MoviesDataSource

class MoviesRepository(dataSource: MoviesDataSource) {

    val allMovies = dataSource.allMovies

    val allGenres = dataSource.allGenres
}