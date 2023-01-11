package com.test.tmdbclient.presentation.di.core

import com.test.tmdbclient.data.db.ArtistDao
import com.test.tmdbclient.data.db.MovieDao
import com.test.tmdbclient.data.db.TvShowDao
import com.test.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.test.tmdbclient.data.repository.artist.datasourceImpl.ArtistLocalDataSourceImpl
import com.test.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.test.tmdbclient.data.repository.movie.datasourceImpl.MovieLocalDataSourceImpl
import com.test.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.test.tmdbclient.data.repository.tvshow.datasourceImpl.TvShowLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Singleton
    @Provides
    fun provideMovieLocalDataSource(movieDao: MovieDao): MovieLocalDataSource{
        return MovieLocalDataSourceImpl(movieDao)
    }

    @Singleton
    @Provides
    fun provideTvShowsLocalDataSource(tvShowDao: TvShowDao): TvShowLocalDataSource {
        return TvShowLocalDataSourceImpl(tvShowDao)
    }

    @Singleton
    @Provides
    fun provideArtistLocalDataSource(artistDao: ArtistDao): ArtistLocalDataSource {
        return ArtistLocalDataSourceImpl(artistDao)
    }
}