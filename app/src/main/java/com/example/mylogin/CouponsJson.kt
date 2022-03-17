package com.example.mylogin

import com.google.gson.annotations.SerializedName
import retrofit2.http.Url
import java.net.URL

data class CouponsJson(
    val offer_id: Int,
    val title: String,
    val description: String,
    val label: String,
    val code: String,
    val featured: String,
    val source: String,
    val deeplink : String,
    val affiliate_link: String,
    val cashback_link: String,
    val url: String,
    val image_url: String,
    val brand_logo: String,
    val type: String,
    val merchant_home_page: String,
    val categories: String,
    val start_date: String,
    val end_date: String,
    val status: String,
    val primary_location: String,
    val language: String,
    val rating: Int,
)
