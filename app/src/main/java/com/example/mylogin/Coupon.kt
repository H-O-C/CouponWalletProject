package com.example.mylogin


import com.google.gson.annotations.SerializedName

data class Coupon(
    val offers: List<Offer>,
    val result: Boolean
)