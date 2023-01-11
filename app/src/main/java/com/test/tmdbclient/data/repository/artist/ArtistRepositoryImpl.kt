package com.test.tmdbclient.data.repository.artist

import android.util.Log
import com.test.tmdbclient.domain.repository.ArtistRepository
import com.test.tmdbclient.data.model.artist.Artist
import com.test.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.test.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.test.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource

class ArtistRepositoryImpl(
    private val artistRemoteDataSource: ArtistRemoteDataSource,
    private val artistLocalDataSource: ArtistLocalDataSource,
    private val artistCacheDataSource: ArtistCacheDataSource
): ArtistRepository {
    override suspend fun getArtists(): List<Artist> = getArtistsFromCache()

    override suspend fun updateArtists(): List<Artist>? {
        val newListOfArtist = getArtistsFromAPI()
        artistLocalDataSource.clearAll()
        artistLocalDataSource.saveArtistToDB(newListOfArtist)
        artistCacheDataSource.saveArtistToCache(newListOfArtist)
        return newListOfArtist
    }

    suspend fun getArtistsFromAPI():List<Artist>{
        lateinit var artistList: List<Artist>

        try {
            val response = artistRemoteDataSource.getArtists()
            val body = response.body()
            if (body != null){
                artistList = body.artists
            }
        }catch (e:Exception){
            Log.d("MyTag", e.message.toString())
        }
        return artistList
    }

    suspend fun getArtistsFromDB(): List<Artist>{
        lateinit var artistList: List<Artist>

        try {

            artistList = artistLocalDataSource.getArtistFromDB()

        }catch (e:Exception){
            Log.d("MyTag", e.message.toString())
        }
        if (artistList.size>0) {
            return artistList
        }else {
            artistList = getArtistsFromAPI()
            artistLocalDataSource.saveArtistToDB(artistList)
            return artistList
        }
    }

    suspend fun getArtistsFromCache(): List<Artist>{

        lateinit var artistList: List<Artist>

        try {

            artistList = artistCacheDataSource.getArtistsFromCache()

        }catch (e:Exception){
            Log.d("MyTag", e.message.toString())
        }
        if (artistList.size>0) {
            return artistList
        }else {
            artistList = getArtistsFromDB()
            artistCacheDataSource.saveArtistToCache(artistList)
            return artistList
        }
    }

}