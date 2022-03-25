package com.example.mylogin

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*


object APIBUILDER {

    private var random: Random = Random()
    var Check: Int = random.nextInt(16)

    private val result = when (Check) {
        in 0..5 -> {
            "961a03aaba832c863f43abeb7866b1d7"
        }
        in 6..10 -> {
            "3a1ff1fa2224f2a98f326bfaf0661ea8"
        }
        in 11..15 -> {
            "bf944b0aefbc4ad84264f0c5eab8e7bd"
        }
        else -> {
            "961a03aaba832c863f43abeb7866b1d7"
        }
    }


    private val apkey = result //The API KEY
    private const val incremental = true

    //$last_extract_datetime = ; // Ideally leave this blank. Populate only if you want to override last extract time stored in the system
    private const val format = "json"
    private const val off_record =
        false // If set to true, system will not update your last extract time. This is useful for debugging purpose. Please note, this will still count in your API Usage limit

    //This calls the API
    //var last_extract = (empty(valast_extract_datetime) ? '' : strtotime($last_extract_datetime) )
    private val URL =
        "https://couponapi.org/api/getFeed/?API_KEY=" + apkey + "&incremental=" + incremental + "format=" + format + "off_record=" + off_record

    //CREATE HTTP CLIENT
    private val okHttp = OkHttpClient.Builder()


    val apiResponse =
        URL("https://couponapi.org/api/getFeed/?API_KEY=" + apkey + "&incremental=" + incremental + "format=" + format + "off_record=" + off_record).readText()


    fun GetJasonData(): String? {
        return try {
            val Url =
                URL("https://couponapi.org/api/getFeed/?API_KEY=" + apkey + "&incremental=" + incremental + "format=" + format + "off_record=" + off_record)
            val connection: HttpURLConnection = Url.openConnection() as HttpURLConnection
            val `is`: InputStream = connection.getInputStream()
            val br = BufferedReader(InputStreamReader(`is`))
            val sb = StringBuilder()
            var line: String?
            while (br.readLine().also { line = it } != null) {
                sb.append(line)
            }
            line = sb.toString()
            connection.disconnect()
            `is`.close()
            sb.delete(0, sb.length)
            line
        } catch (e: Exception) {
          return null
        }
    }




    //retrofit builder
    private val builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())


    //create retrofit Instance
    private val retrofit = builder.build()

    //we will use this class to create an anonymous inner class function that
    //implements Country service Interface


    fun <T> buildService (serviceType :Class<T>):T{
        return retrofit.create(serviceType)
    }



}


