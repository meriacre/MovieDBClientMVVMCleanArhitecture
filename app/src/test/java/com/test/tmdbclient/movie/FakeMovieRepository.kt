package com.test.tmdbclient.movie

import com.test.tmdbclient.data.model.movie.Movie
import com.test.tmdbclient.domain.repository.MovieRepository

class FakeMovieRepository: MovieRepository {

    private val movies = mutableListOf<Movie>()

    init {
        movies.add(Movie(1, "overview1", "posterPath1", "date1", "title1"))
        movies.add(Movie(2, "overview2", "posterPath2", "date2", "title2"))
        movies.add(Movie(3, "overview3", "posterPath3", "date3", "title3"))
    }

    override suspend fun getMovies(): List<Movie>? {
        return movies
    }

    override suspend fun updateMovies(): List<Movie>? {
        movies.clear()
        movies.add(Movie(4, "overview1", "posterPath1", "date1", "title1"))
        movies.add(Movie(5, "overview2", "posterPath2", "date2", "title2"))
      return movies
    }
}