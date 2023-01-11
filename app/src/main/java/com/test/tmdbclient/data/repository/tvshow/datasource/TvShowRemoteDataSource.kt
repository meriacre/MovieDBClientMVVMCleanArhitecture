package com.test.tmdbclient.data.repository.tvshow.datasource

import com.test.tmdbclient.data.model.movie.MovieList
import com.test.tmdbclient.data.model.tvshow.TvShowList
import retrofit2.Response

interface TvShowRemoteDataSource {
    suspend fun getTvShows(): Response<TvShowList>
}