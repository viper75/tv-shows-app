package org.viper75.tvshows.models

import com.google.gson.annotations.SerializedName

data class TvShow(
    private val id: Int,
    private val name: String,
    private val country: String,
    private val network: String,
    private val status: String,
    @SerializedName("image_thumbnail_path")
    private val image: String,
    @SerializedName("start_date")
    private val startDate: String,
    @SerializedName("end_date")
    private val endDate: String,
)
