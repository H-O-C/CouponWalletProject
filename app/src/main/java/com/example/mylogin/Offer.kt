package com.example.mylogin


import com.google.gson.annotations.SerializedName

data class Offer(
    @SerializedName("affiliate_link")
    val affiliateLink: String,
    @SerializedName("brand_logo")
    val brandLogo: String,
    @SerializedName("cashback_link")
    val cashbackLink: String,
    @SerializedName("categories")
    val categories: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("deeplink")
    val deeplink: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("end_date")
    val endDate: Any,
    @SerializedName("featured")
    val featured: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("label")
    val label: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("merchant_home_page")
    val merchantHomePage: String,
    @SerializedName("offer_id")
    val offerId: String,
    @SerializedName("primary_location")
    val primaryLocation: String,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("source")
    val source: String,
    @SerializedName("start_date")
    val startDate: Any,
    @SerializedName("status")
    val status: String,
    @SerializedName("store")
    val store: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)