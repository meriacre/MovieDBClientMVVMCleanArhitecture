package com.test.tmdbclient.data.repository.movie.datasource

import com.test.tmdbclient.data.model.movie.MovieList
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun getMovies(): Response<MovieList>
}