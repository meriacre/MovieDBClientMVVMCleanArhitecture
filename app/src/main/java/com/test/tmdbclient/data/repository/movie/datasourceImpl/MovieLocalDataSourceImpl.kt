package com.test.tmdbclient.data.repository.movie.datasourceImpl

import com.test.tmdbclient.data.db.MovieDao
import com.test.tmdbclient.data.model.movie.Movie
import com.test.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieLocalDataSourceImpl(private val movieDao: MovieDao): MovieLocalDataSource {
    override suspend fun getMoviesFromDB(): List<Movie> {
        return  movieDao.getMovies()
    }

    override suspend fun saveMoviesToDB(movie: List<Movie>) {
       CoroutineScope(Dispatchers.IO).launch {
           movieDao.saveMovies(movie)
    }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            movieDao.deleteAllMovies()
        }
    }
}