package com.android.simple.movie.list.app.viewmodels

import com.android.simple.movie.list.app.data.models.Genre
import com.android.simple.movie.list.app.data.models.Movie
import com.android.simple.movie.list.app.data.repositories.MoviesRepository
import com.android.simple.movie.list.app.ui.viewmodels.MoviesConnector
import com.android.simple.movie.list.app.uitls.behavior
import io.reactivex.rxjava3.subjects.BehaviorSubject
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import java.util.*

class MoviesViewModelTest {

    private lateinit var moviesRepository: MoviesRepository
    private lateinit var moviesConnector: MoviesConnector

    @Before
    fun init() {
        moviesRepository = mock(MoviesRepository::class.java)
    }

    /** Checks correct behavior of [MoviesConnector.onShowAllMovies]. */
    @Test
    fun test_onShowAllMovies() {
        moviesRepository.apply {
            Mockito.`when`(allMovies).thenReturn(behavior())
            Mockito.`when`(allGenres).thenReturn(behavior())
        }

        moviesConnector = MoviesConnector(moviesRepository)

        val showAllMoviesTest = moviesConnector.onShowAllMovies.test()
        showAllMoviesTest.assertValueCount(0)

        // 1 Add one movie to allMovies ------------------------------------------------------------

        val movies = ArrayList<Movie>()

        val genre1 = Genre(UUID.randomUUID(), "genre1")
        val genre2 = Genre(UUID.randomUUID(), "genre2")

        movies.add(
            Movie(
                uuid = UUID.randomUUID(),
                title = "Movie1",
                directors = listOf("Director1"),
                year = "2000",
                genres = listOf(genre1)
            )
        )

        (moviesRepository.allMovies as BehaviorSubject).onNext(movies)

        showAllMoviesTest.assertValueCount(1)
        showAllMoviesTest.assertValueLast { it.size == 1 }
        showAllMoviesTest.assertValueLast {
            it.size == 1 && it.first().title == "Movie1" && it.first().genres[0].name == "genre1"
        }

        // 2 Add genre1 to filters -----------------------------------------------------------------

        moviesConnector.genreFilters.onNext(listOf(genre1))

        showAllMoviesTest.assertValueCount(2)
        showAllMoviesTest.assertValueLast { it.size == 1 }

        // 3 Add genre2 to filters -----------------------------------------------------------------

        moviesConnector.genreFilters.onNext(listOf(genre2))

        showAllMoviesTest.assertValueCount(3)
        showAllMoviesTest.assertValueLast { it.isEmpty() }

        // 4 Add second movie to allMovies with filters containing genre2 --------------------------

        movies.add(
            Movie(
                uuid = UUID.randomUUID(),
                title = "Movie2",
                directors = listOf("Director2"),
                year = "2001",
                genres = listOf(genre2)
            )
        )

        (moviesRepository.allMovies as BehaviorSubject).onNext(movies)

        showAllMoviesTest.assertValueCount(4)
        showAllMoviesTest.assertValueLast { it.size == 1 }

        // 5 Clear filters -------------------------------------------------------------------------

        moviesConnector.genreFilters.onNext(emptyList())

        showAllMoviesTest.assertValueCount(5)
        showAllMoviesTest.assertValueLast { it.size == 2 }
    }

    /** Checks correct behavior of [MoviesConnector.onShowFilters]. */
    @Test
    fun test_onShowFilters() {
        moviesRepository.apply {
            Mockito.`when`(allMovies).thenReturn(behavior())
            Mockito.`when`(allGenres).thenReturn(behavior())
        }

        moviesConnector = MoviesConnector(moviesRepository)

        val showFilters = moviesConnector.onShowFilters.test()
        showFilters.assertValueCount(0)

        // 1 Add one genre to allGenres stream -----------------------------------------------------

        val genres = ArrayList<Genre>()

        val genre1UUID = UUID.randomUUID()

        genres.add(Genre(genre1UUID, "genre1"))

        moviesRepository.allGenres.onNext(genres)
        moviesConnector.onFiltersButtonClick.onNext(Unit)

        showFilters.assertValueCount(1)
        showFilters.assertValueLast { it.size == 1 && it.first().first.name == "genre1" && !it.first().second }

        // 2 Add the same genre to genreFilters stream ---------------------------------------------

        moviesConnector.genreFilters.onNext(genres)
        moviesConnector.onFiltersButtonClick.onNext(Unit)

        showFilters.assertValueCount(2)
        showFilters.assertValueLast { it.size == 1 && it.first().first.name == "genre1" && it.first().second }
    }
}