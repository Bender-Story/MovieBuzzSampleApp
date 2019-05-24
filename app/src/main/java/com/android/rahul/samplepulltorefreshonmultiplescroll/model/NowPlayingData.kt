package com.android.rahul.samplepulltorefreshonmultiplescroll.model

import com.google.gson.annotations.SerializedName

data class NowPlayingData(
    @SerializedName("dates") val dates: Dates,
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<Result>,
    @SerializedName("total_pages") val total_pages: Int,
    @SerializedName("total_results") val total_results: Int
)