package com.example.mylogin

import okhttp3.Interceptor
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

private const val apkey = "3a1ff1fa2224f2a98f326bfaf0661ea8" //The API KEY
private const val _incremental = true

//$last_extract_datetime = ; // Ideally leave this blank. Populate only if you want to override last extract time stored in the system
private const val _format = "json"
private const val _off_record =
    false // If set to true, system will not update your last extract time. This is useful for debugging purpose. Please note, this will still count in your API Usage limit

interface CouponService
{



    @GET("getFeed")
    fun getAffectedCouponList (
        @Query("incremental") incremental : String = "true",
        @Query ("format") format : String = _format,
        @Query ("off_record") off_record : String = "false",
    ) : Call<Coupons>
    companion object {
        operator fun invoke() {
            val requestInterceptor = Interceptor { chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("API_KEY", apkey)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }
        }
    }
}