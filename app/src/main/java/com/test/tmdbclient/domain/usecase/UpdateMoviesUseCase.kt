package com.test.tmdbclient.domain.usecase

import com.test.tmdbclient.domain.repository.MovieRepository
import com.test.tmdbclient.data.model.movie.Movie

class UpdateMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(): List<Movie>? = movieRepository.updateMovies()
}