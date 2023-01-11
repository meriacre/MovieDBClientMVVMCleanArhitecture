package com.test.tmdbclient.domain.repository

import com.test.tmdbclient.data.model.movie.Movie
import com.test.tmdbclient.data.model.tvshow.TvShow

interface TvShowRepository {
    suspend fun getTvShows(): List<TvShow>?
    suspend fun updateTvShow(): List<TvShow>?
}