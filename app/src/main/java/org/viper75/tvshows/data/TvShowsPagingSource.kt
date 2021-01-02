package org.viper75.tvshows.data

import androidx.paging.PagingSource
import org.viper75.tvshows.api.EpisodateApiResponse
import org.viper75.tvshows.api.EpisodateApiService
import org.viper75.tvshows.repository.TvShowRepository
import retrofit2.HttpException
import java.io.IOException

class TvShowsPagingSource(
    private val episodateApiService: EpisodateApiService,
    private val query: String?
) : PagingSource<Int, TvShow>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvShow> {
        val currentPage = params.key ?: 1

        return try {
            val response = if (query != null) episodateApiService.search(query, currentPage)
                else episodateApiService.mostPopular(currentPage)

            val tvShows = response.tvShows

            LoadResult.Page(
                data = tvShows,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (tvShows.isEmpty()) null else currentPage + 1
            )
        } catch (ex: IOException) {
            LoadResult.Error(ex)
        } catch (ex: HttpException) {
            LoadResult.Error(ex)
        }
    }
}