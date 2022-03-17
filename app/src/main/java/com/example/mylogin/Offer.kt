package com.example.mylogin


import com.google.gson.annotations.SerializedName

data class Offer(
    @SerializedName("affiliate_link")
    val affiliateLink: String,
    @SerializedName("brand_logo")
    val brandLogo: String,
    @SerializedName("cashback_link")
    val cashbackLink: String,
    val categories: String,
    val code: String,
    val deeplink: String,
    val description: String,
    @SerializedName("end_date")
    val endDate: Any,
    val featured: String,
    @SerializedName("image_url")
    val imageUrl: String,
    val label: String,
    val language: String,
    @SerializedName("merchant_home_page")
    val merchantHomePage: String,
    @SerializedName("offer_id")
    val offerId: String,
    @SerializedName("primary_location")
    val primaryLocation: String,
    val rating: Int,
    val source: String,
    @SerializedName("start_date")
    val startDate: Any,
    val status: String,
    val store: String,
    val title: String,
    val type: String,
    val url: String
)