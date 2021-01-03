package org.viper75.tvshows.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShow(
    val id: Int,
    val name: String,
    val country: String,
    val network: String,
    val status: String,
    @SerializedName("image_thumbnail_path")
    val image: String,
    @SerializedName("start_date")
    val startDate: String?,
    @SerializedName("end_date")
    val endDate: String,
): Parcelable
