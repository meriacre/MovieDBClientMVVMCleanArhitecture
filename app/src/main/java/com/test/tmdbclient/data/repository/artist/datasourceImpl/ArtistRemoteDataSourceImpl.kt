package com.test.tmdbclient.data.repository.artist.datasourceImpl

import com.test.tmdbclient.data.api.TMDBService
import com.test.tmdbclient.data.model.artist.ArtistList
import com.test.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import retrofit2.Response

class ArtistRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey:String
    ): ArtistRemoteDataSource {
    override suspend fun getArtists(): Response<ArtistList> = tmdbService.getPopularArtist(apiKey)
}