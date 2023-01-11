package com.test.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.tmdbclient.data.model.artist.Artist
import com.test.tmdbclient.data.model.movie.Movie

@Dao
interface ArtistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArtist(movie: List<Artist>)

    @Query("Delete From popular_artists")
    suspend fun deleteAllArtists()

    @Query(value = "SELECT * FROM popular_artists")
    suspend fun getArtists(): List<Artist>
}