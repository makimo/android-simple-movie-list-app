package com.android.simple.movie.list.app.data.datasources

import com.android.simple.movie.list.app.data.models.Genre
import com.android.simple.movie.list.app.data.models.Movie
import com.android.simple.movie.list.app.data.models.MovieResponse
import com.android.simple.movie.list.app.uitls.defaultBehavior
import com.android.simple.movie.list.app.uitls.getFileFromAssets
import com.google.gson.Gson
import java.util.*

class MoviesDataSourceImpl : MoviesDataSource {

    companion object {
        const val GENRES_FILE = "genres.json"
        const val MOVIES_FILE = "movies.json"
    }

    override val rawMovies = defaultBehavior<List<MovieResponse>>(emptyList())

    override val allGenres = defaultBehavior<List<Genre>>(emptyList())

    override val allMovies = rawMovies.map {
        it.map {
            Movie(
                uuid = it.uuid,
                title = it.title,
                directors = it.directors,
                year = it.year,
                genres = getGenresByUUID(it.genres)
            )
        }
    }

    private val gson by lazy { Gson() }

    init {
        getAllGenres()
        getRawMovies()
    }

    private fun getRawMovies() {
        val fileString = getFileFromAssets(MOVIES_FILE)
        val movies = gson.fromJson(fileString, Array<MovieResponse>::class.java)
        rawMovies.onNext(movies.toList())
    }

    private fun getAllGenres() {
        val fileString = getFileFromAssets(GENRES_FILE)
        val genres = gson.fromJson(fileString, Array<Genre>::class.java)
        allGenres.onNext(genres.toList())
    }

    private fun getGenresByUUID(movieGenreUuids: List<UUID>): List<Genre> {
        val genres = allGenres.value ?: emptyList()

        return movieGenreUuids.flatMap { genreUuid ->
            genres.filter { it.uuid == genreUuid }
        }
    }
}