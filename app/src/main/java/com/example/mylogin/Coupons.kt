package com.example.mylogin


import com.google.gson.annotations.SerializedName

data class Coupons(
    @SerializedName("offers")
    val offers: MutableList<Offer>,
    @SerializedName("result")
    val result: Boolean
)

