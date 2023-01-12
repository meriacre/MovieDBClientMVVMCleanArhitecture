package com.test.tmdbclient.presentation.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.test.tmdbclient.data.model.movie.Movie
import com.test.tmdbclient.domain.usecase.GetMoviesUseCase
import com.test.tmdbclient.domain.usecase.UpdateMoviesUseCase
import com.test.tmdbclient.getOrAwaitValue
import com.test.tmdbclient.movie.FakeMovieRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        val fakeMovieRepository = FakeMovieRepository()
        val getMovieUseCase = GetMoviesUseCase(fakeMovieRepository)
        val updateMovieUseCase = UpdateMoviesUseCase(fakeMovieRepository)
        viewModel = MovieViewModel(getMovieUseCase, updateMovieUseCase)
    }

    @Test
    fun getMovies_returnsCurrentList(){
        val movies = mutableListOf<Movie>()
        movies.add(Movie(1, "overview1", "posterPath1", "date1", "title1"))
        movies.add(Movie(2, "overview2", "posterPath2", "date2", "title2"))
        movies.add(Movie(3, "overview3", "posterPath3", "date3", "title3"))

        val currentList = viewModel.getMovies().getOrAwaitValue()
        assertThat(currentList).isEqualTo(movies)
    }

    @Test
    fun updateMovies_returnsUpdatedList(){
        val movies = mutableListOf<Movie>()
        movies.add(Movie(4, "overview1", "posterPath1", "date1", "title1"))
        movies.add(Movie(5, "overview2", "posterPath2", "date2", "title2"))

        val updatedList = viewModel.updateMovies().getOrAwaitValue()
        assertThat(updatedList).isEqualTo(movies)
    }
}