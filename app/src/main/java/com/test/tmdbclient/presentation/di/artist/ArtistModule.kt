package com.test.tmdbclient.presentation.di.artist

import com.test.tmdbclient.domain.usecase.GetArtistUseCase
import com.test.tmdbclient.domain.usecase.UpdateArtistUseCase
import com.test.tmdbclient.presentation.artist.ArtistViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ArtistModule {

    @ArtistScope
    @Provides
    fun provideArtistViewModelFactory(getArtistUseCase: GetArtistUseCase, updateArtistUseCase: UpdateArtistUseCase): ArtistViewModelFactory{
        return ArtistViewModelFactory(
            getArtistUseCase,
            updateArtistUseCase
        )
    }
}