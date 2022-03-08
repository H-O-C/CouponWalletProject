package com.example.mylogin

import retrofit2.Call
import retrofit2.http.GET

interface CouponService
{
    @GET("coupons")
    fun getAffectedCouponList () : Call<List<CouponsJson>>
}