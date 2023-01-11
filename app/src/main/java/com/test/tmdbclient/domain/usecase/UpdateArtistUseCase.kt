package com.test.tmdbclient.domain.usecase

import com.test.tmdbclient.domain.repository.ArtistRepository
import com.test.tmdbclient.data.model.artist.Artist

class UpdateArtistUseCase(private val artistRepository: ArtistRepository) {
    suspend fun execute(): List<Artist>? = artistRepository.updateArtists()
}