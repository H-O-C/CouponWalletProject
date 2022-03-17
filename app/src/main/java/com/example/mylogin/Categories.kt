package com.example.mylogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_categories.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Categories : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
    var coupList: MutableList<Offer> = ArrayList()
        loadCoupons(coupList)

    }

    private fun loadCoupons(_coupList : MutableList<Offer>) {
        //initiate the service
        val destinationService = APIBUILDER.buildService(CouponService::class.java)
        val requestCall = destinationService.getAffectedCouponList()
        //make network call asynchronously
        requestCall.enqueue(object : Callback<Coupons> {
            override fun onResponse(call: Call<Coupons>, response: Response<Coupons>) {
                if (response.isSuccessful) {
                    val couponList = response.body()!!
                    val couponName = findViewById<TextView>(R.id.testview)
                    couponName.text = couponList.offers[1].toString()

                } else {
                    Toast.makeText(
                        this@Categories,
                        "Something went wrong ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Coupons>, t: Throwable) {
                Toast.makeText(this@Categories, "Something went wrong $t", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }


}



