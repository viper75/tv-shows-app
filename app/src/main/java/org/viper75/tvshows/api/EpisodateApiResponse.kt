package org.viper75.tvshows.api

import com.google.gson.annotations.SerializedName
import org.viper75.tvshows.data.TvShow

data class EpisodateApiResponse(
    @SerializedName("tv_shows")
    val tvShows: List<TvShow>
)