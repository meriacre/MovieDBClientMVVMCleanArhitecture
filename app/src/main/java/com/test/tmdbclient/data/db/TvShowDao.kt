package com.test.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.tmdbclient.data.model.movie.Movie
import com.test.tmdbclient.data.model.tvshow.TvShow

@Dao
interface TvShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTvShows(movie: List<TvShow>)

    @Query("Delete From popular_tvShows")
    suspend fun deleteAllShows()

    @Query(value = "SELECT * FROM popular_tvShows")
    suspend fun getShows(): List<TvShow>
}