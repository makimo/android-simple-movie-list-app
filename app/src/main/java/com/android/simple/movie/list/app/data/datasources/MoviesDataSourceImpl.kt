package com.android.simple.movie.list.app.data.datasources

import com.android.simple.movie.list.app.data.models.Genre
import com.android.simple.movie.list.app.data.models.Movie
import com.android.simple.movie.list.app.uitls.defaultBehavior
import com.android.simple.movie.list.app.uitls.getFileFromAssets
import com.google.gson.Gson
import java.util.*

class MoviesDataSourceImpl : MoviesDataSource {

    companion object {
        const val GENRES_FILE = "genres.json"
        const val MOVIES_FILE = "movies.json"
    }

    override val allMovies = defaultBehavior<List<Movie>>(emptyList())

    override val allGenres = defaultBehavior<List<Genre>>(emptyList())

    private val gson by lazy { Gson() }

    init {
        getAllGenres()
        getAllMovies()
    }

    private fun getAllMovies() {
        val fileString = getFileFromAssets(MOVIES_FILE)
        val movies = gson.fromJson(fileString, Array<Movie>::class.java)
        allMovies.onNext(movies.toList())
    }

    private fun getAllGenres() {
        val fileString = getFileFromAssets(GENRES_FILE)
        val genres = gson.fromJson(fileString, Array<Genre>::class.java)
        allGenres.onNext(genres.toList())
    }
}