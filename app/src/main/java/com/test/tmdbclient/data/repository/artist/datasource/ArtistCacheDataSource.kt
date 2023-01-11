package com.test.tmdbclient.data.repository.artist.datasource

import com.test.tmdbclient.data.model.artist.Artist
import com.test.tmdbclient.data.model.movie.Movie

interface ArtistCacheDataSource {
    suspend fun getArtistsFromCache(): List<Artist>
    suspend fun saveArtistToCache(artist: List<Artist>)
}