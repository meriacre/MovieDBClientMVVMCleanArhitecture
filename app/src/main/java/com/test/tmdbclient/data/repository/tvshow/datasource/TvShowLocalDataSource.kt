package com.test.tmdbclient.data.repository.tvshow.datasource

import com.test.tmdbclient.data.model.movie.Movie
import com.test.tmdbclient.data.model.tvshow.TvShow

interface TvShowLocalDataSource {
    suspend fun getTvShowFromDB(): List<TvShow>
    suspend fun saveTvShowToDB(tvShow: List<TvShow>)
    suspend fun clearAll()
}