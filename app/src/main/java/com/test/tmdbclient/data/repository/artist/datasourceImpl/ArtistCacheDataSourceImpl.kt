package com.test.tmdbclient.data.repository.artist.datasourceImpl

import com.test.tmdbclient.data.model.artist.Artist
import com.test.tmdbclient.data.model.movie.Movie
import com.test.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource

class ArtistCacheDataSourceImpl: ArtistCacheDataSource {

    private var artistList =  ArrayList<Artist>()

    override suspend fun getArtistsFromCache(): List<Artist> {
        return artistList
    }

    override suspend fun saveArtistToCache(artist: List<Artist>) {
        artistList.clear()
        artistList = ArrayList(artist)
    }
}