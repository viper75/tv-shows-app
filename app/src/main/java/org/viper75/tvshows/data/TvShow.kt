package org.viper75.tvshows.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
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
): Parcelable
