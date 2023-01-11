package com.test.tmdbclient.data.repository.tvshow.datasourceImpl

import com.test.tmdbclient.data.model.movie.Movie
import com.test.tmdbclient.data.model.tvshow.TvShow
import com.test.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource

class TvShowCacheDataSourceImpl:TvShowCacheDataSource {

    private var tvShowList =  ArrayList<TvShow>()

    override suspend fun getTvShowFromCache(): List<TvShow> = tvShowList

    override suspend fun saveTvShowToCache(tvShow: List<TvShow>) {
        tvShowList.clear()
        ArrayList(tvShow)
    }
}