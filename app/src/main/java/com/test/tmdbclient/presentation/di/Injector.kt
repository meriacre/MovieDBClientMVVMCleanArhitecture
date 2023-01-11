package com.test.tmdbclient.presentation.di

import com.test.tmdbclient.presentation.di.artist.ArtistSubComponent
import com.test.tmdbclient.presentation.di.movie.MovieSubComponent
import com.test.tmdbclient.presentation.di.tvshow.TvShowSubComponent

interface Injector {
    fun createMovieSubComponent(): MovieSubComponent
    fun createTvShowSubComponent(): TvShowSubComponent
    fun createArtistSubComponent(): ArtistSubComponent
}