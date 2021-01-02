package org.viper75.tvshows.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import org.viper75.tvshows.data.TvShow
import org.viper75.tvshows.repository.TvShowRepository

class MostPopularShowsViewModel @ViewModelInject constructor(
    private val repository: TvShowRepository
) : ViewModel() {

    var tvShows = MutableLiveData<PagingData<TvShow>>()

    fun mostPopular() {
        tvShows = repository.mostPopular() as MutableLiveData<PagingData<TvShow>>
    }
}