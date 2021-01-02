package org.viper75.tvshows.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import org.viper75.tvshows.api.EpisodateApiService
import org.viper75.tvshows.data.TvShowsPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvShowRepository @Inject constructor(
    private val episodateApiService: EpisodateApiService
) {

    fun mostPopular() = Pager(
        config = PagingConfig(
            maxSize = 100,
            pageSize = 20,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { TvShowsPagingSource(episodateApiService, null) }
    ).liveData

    fun search(query: String) = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { TvShowsPagingSource(episodateApiService, query) }
    ).liveData
}