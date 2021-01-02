package org.viper75.tvshows.api

import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodateApiService {

    companion object {
        const val BASE_URL = "https://www.episodate.com/api/"
    }

    @GET("most-popular")
    suspend fun mostPopular(@Query("page") page: Int): EpisodateApiResponse

    @GET("search")
    suspend fun search(@Query("q") query: String, @Query("page") page: Int): EpisodateApiResponse
}