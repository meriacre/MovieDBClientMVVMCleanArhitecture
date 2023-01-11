package com.test.tmdbclient.domain.usecase

import com.test.tmdbclient.domain.repository.TvShowRepository
import com.test.tmdbclient.data.model.tvshow.TvShow

class UpdateTvShowUseCase(private val tvShowRepository: TvShowRepository) {
    suspend fun execute(): List<TvShow>? = tvShowRepository.updateTvShow()
}