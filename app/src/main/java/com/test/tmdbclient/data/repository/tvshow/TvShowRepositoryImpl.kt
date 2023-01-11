package com.test.tmdbclient.data.repository.tvshow

import android.util.Log
import com.test.tmdbclient.data.model.tvshow.TvShow
import com.test.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.test.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.test.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.test.tmdbclient.domain.repository.TvShowRepository

class TvShowRepositoryImpl(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource,
    private val tvShowLocalDataSource: TvShowLocalDataSource,
    private val tvShowCacheDataSource: TvShowCacheDataSource

): TvShowRepository {
    override suspend fun getTvShows(): List<TvShow> = getTvShowFromCache()

    override suspend fun updateTvShow(): List<TvShow>? {
        val newListOfTvShow = getTvShowFromAPI()
        tvShowLocalDataSource.clearAll()
        tvShowLocalDataSource.saveTvShowToDB(newListOfTvShow)
        tvShowCacheDataSource.saveTvShowToCache(newListOfTvShow)
        return newListOfTvShow
    }

    suspend fun getTvShowFromAPI():List<TvShow>{
        lateinit var tvShowList: List<TvShow>

        try {
            val response = tvShowRemoteDataSource.getTvShows()
            val body = response.body()
            if (body != null){
                tvShowList = body.tvShows
            }
        }catch (e:Exception){
            Log.d("MyTag", e.message.toString())
        }
        return tvShowList
    }

    suspend fun getTvShowFromDB(): List<TvShow>{
        lateinit var tvShowList: List<TvShow>

        try {

            tvShowList = tvShowLocalDataSource.getTvShowFromDB()

        }catch (e:Exception){
            Log.d("MyTag", e.message.toString())
        }
        if (tvShowList.size>0) {
            return tvShowList
        }else {
            tvShowList = getTvShowFromAPI()
            tvShowLocalDataSource.saveTvShowToDB(tvShowList)
            return tvShowList
        }
    }

    suspend fun getTvShowFromCache(): List<TvShow>{

        lateinit var tvShowList: List<TvShow>

        try {

            tvShowList = tvShowCacheDataSource.getTvShowFromCache()

        }catch (e:Exception){
            Log.d("MyTag", e.message.toString())
        }
        if (tvShowList.size>0) {
            return tvShowList
        }else {
            tvShowList = getTvShowFromDB()
            tvShowCacheDataSource.saveTvShowToCache(tvShowList)
            return tvShowList
        }
    }
}