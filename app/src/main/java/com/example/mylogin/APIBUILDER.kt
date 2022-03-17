package com.example.mylogin

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object APIBUILDER

{
    private const val apkey = "ef2432bed91590bfa990bc164025b7cf" //The API KEY
    private const val incremental = true

    //$last_extract_datetime = ; // Ideally leave this blank. Populate only if you want to override last extract time stored in the system
    private const val format = "json"
    private const val off_record =
        false // If set to true, system will not update your last extract time. This is useful for debugging purpose. Please note, this will still count in your API Usage limit

    //This calls the API
    //var last_extract = (empty(valast_extract_datetime) ? '' : strtotime($last_extract_datetime) )
    private const val URL =
        "https://couponapi.org/api/getFeed/?API_KEY=" + apkey + "&incremental=" + incremental + "&format=" + format + "&off_record=" + off_record

    private const val testURL ="https://couponapi.org/"
    //CREATE HTTP CLIENT
    private val okHttp = OkHttpClient.Builder()

    //retrofit builder
    private val builder = Retrofit.Builder().baseUrl(testURL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())


    //create retrofit Instance
    private val retrofit = builder.build()

    //we will use this class to create an anonymous inner class function that
    //implements Coupon service Interface


    fun <T> buildService (serviceType :Class<T>):T{
        return retrofit.create(serviceType)
    }



}
