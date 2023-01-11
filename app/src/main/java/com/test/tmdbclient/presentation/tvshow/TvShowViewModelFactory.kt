package com.test.tmdbclient.presentation.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.tmdbclient.domain.usecase.GetTvShowUseCase
import com.test.tmdbclient.domain.usecase.UpdateTvShowUseCase

class TvShowViewModelFactory(
    private val getTvShow: GetTvShowUseCase,
    private val updateTvShow: UpdateTvShowUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TvShowViewModel(getTvShow, updateTvShow) as T
    }
}