package org.viper75.tvshows.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import org.viper75.tvshows.data.TvShow
import org.viper75.tvshows.repository.TvShowRepository

class MostPopularShowsViewModel @ViewModelInject constructor(
    private val repository: TvShowRepository,
    @Assisted state: SavedStateHandle
) : ViewModel() {

    private val currentQuery = state.getLiveData(CURRENT_QUERY, "")

    var tvShows = currentQuery.switchMap { query ->
        if (query == "")
            repository.mostPopular().cachedIn(viewModelScope)
        else
            repository.search(query).cachedIn(viewModelScope)
    }

    fun search(query: String) {
        currentQuery.value = query
    }

    companion object {
        private const val CURRENT_QUERY = "current_query"
    }
}