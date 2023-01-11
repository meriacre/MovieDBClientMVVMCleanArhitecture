package com.test.tmdbclient.data.repository.tvshow.datasourceImpl

import com.test.tmdbclient.data.db.MovieDao
import com.test.tmdbclient.data.db.TvShowDao
import com.test.tmdbclient.data.model.tvshow.TvShow
import com.test.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvShowLocalDataSourceImpl(private val tvShowDao: TvShowDao): TvShowLocalDataSource {
    override suspend fun getTvShowFromDB(): List<TvShow> = tvShowDao.getShows()

    override suspend fun saveTvShowToDB(tvShow: List<TvShow>) {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDao.saveTvShows(tvShow)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDao.deleteAllShows()
        }
    }
}