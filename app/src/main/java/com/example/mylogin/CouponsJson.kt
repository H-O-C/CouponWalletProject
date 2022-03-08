package com.example.mylogin

import com.google.gson.annotations.SerializedName
import retrofit2.http.Url
import java.net.URL

data class CouponsJson(
    val offer_id: Int,
    val title: String,
    val description: String,
    val featured: String,
    val source: String,
    val url: Url,
    val affiliate_link: Url,
    val image_url: Url,
    val brand_logo: Url,
    val type: String,
    val merchant_home_page: Url,
    val categories: String,
    val start_date: String,
    val end_date: String,
    val status: String,
    val primary_location: String,
    val rating: Int,
    val label: String,
    val language: String,
    val couponInfos: couponInfo
) {
    data class couponInfo(
        val flag: String,
        @SerializedName("_id")
        val id: Int
    )
}