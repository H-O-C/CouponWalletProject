package com.example.mylogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_categories.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Categories : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
val apiService = CouponService()
        val coupons = apiService.getCoupon()
        val testName = findViewById<TextView>(R.id.testView)
        testName.text = coupons.toString()
    }
    /*
    private fun loadCoupons() {
        //initiate the service
        val destinationService = APIBUILDER.buildService(CouponService::class.java)
        val requestCall = destinationService.getAffectedCouponList("true","json","false")
        //make network call asynchronously
        requestCall.enqueue(object : Callback<List<Offer>> {
            override fun onResponse(call: Call<List<Offer>>, response: Response<List<CouponsJson>>) {
                Log.d("coupons", "onResponse: ${response.body()}")
                if (response.isSuccessful){
                    val couponList = response.body()!!
                    Log.d("coupons", "couponList size : ${couponList.size}")
                    coupon_recycler.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(this@Categories,2)
                        adapter = CouponAdapter(response.body()!!)
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
    */
}

