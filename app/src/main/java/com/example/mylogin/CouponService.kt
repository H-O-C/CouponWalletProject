package com.example.mylogin

import com.google.firebase.inject.Deferred
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "ef2432bed91590bfa990bc164025b7cf" //The API KEY
private const val _incremental = true

//$last_extract_datetime = ; // Ideally leave this blank. Populate only if you want to override last extract time stored in the system
private const val _format = "json"
private const val _off_record =
    false // If set to true, system will not update your last extract time. This is useful for debugging purpose. Please note, this will still count in your API Usage limit

interface CouponService
{
    //https://couponapi.org/api/getFeed/?API_KEY=ef2432bed91590bfa990bc164025b7cf&incremental=true&format=json&off_record=false"
    @GET("getFeed")
    suspend fun getCoupon (
        @Query ("incremental") incremental : String = "true",
        @Query ("format") format : String = _format,
        @Query ("off_record") off_record : String = "false",

    ) : Response<String>

    companion object {
        operator fun invoke(): CouponService {
            val requestInterceptor = Interceptor { chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("API_KEY", API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://couponapi.org/api/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CouponService::class.java)
        }
    }
}