package com.test.tmdbclient.presentation.di.tvshow

import com.test.tmdbclient.domain.usecase.GetTvShowUseCase
import com.test.tmdbclient.domain.usecase.UpdateTvShowUseCase
import com.test.tmdbclient.presentation.tvshow.TvShowViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TvShowModule {

    @TvShowScope
    @Provides
    fun provideTvShowViewModelFactory(getTvShowUseCase: GetTvShowUseCase, updateTvShowUseCase: UpdateTvShowUseCase): TvShowViewModelFactory {
        return TvShowViewModelFactory(
            getTvShowUseCase,
            updateTvShowUseCase
        )
    }
}