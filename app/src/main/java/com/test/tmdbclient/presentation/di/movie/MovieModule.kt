package com.test.tmdbclient.presentation.di.movie

import com.test.tmdbclient.domain.usecase.GetMoviesUseCase
import com.test.tmdbclient.domain.usecase.UpdateMoviesUseCase
import com.test.tmdbclient.presentation.movie.MovieViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MovieModule {

    @MovieScope
    @Provides
    fun provideMovieViewModelFactory(getMovieUseCase: GetMoviesUseCase, updateMoviesUseCase: UpdateMoviesUseCase): MovieViewModelFactory {
        return MovieViewModelFactory(
            getMovieUseCase,
            updateMoviesUseCase
        )
    }
}