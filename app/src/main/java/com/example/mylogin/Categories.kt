package com.example.mylogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_categories.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


class Categories : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        loadCoupons()
    }
    private fun loadCoupons() {
        //initiate the service
        val destinationService = APIBUILDER.buildService(CouponService::class.java)
        val requestCall = destinationService.getAffectedCouponList()
        //make network call asynchronously
        requestCall.enqueue(object : Callback<List<CouponsJson>> {
            override fun onResponse(call: Call<List<CouponsJson>>, response: Response<List<CouponsJson>>) {
                Log.d("Response", "onResponse: ${response.body()}")
                if (response.isSuccessful){
                    val couponList = response.body()!!
                    Log.d("Response", "couponList size : ${couponList.size}")
                    coupon_recycler.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(this@Categories,2)
                        adapter = CountriesAdapter(response.body()!!)
                    }
                }else{
                    Toast.makeText(this@Categories, "Something went wrong ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<CouponsJson>>, t: Throwable) {
                Toast.makeText(this@Categories, "Something went wrong $t", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
//MainActivity
