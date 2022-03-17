package com.example.mylogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.*
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
        val buttondynamic = Button(this)
        val intent = Intent(this, CouponPage::class.java)
        loadCoupons(coupList, intent, buttondynamic)
        val rl = RelativeLayout(this)
        val llmain = LinearLayout(this)

        /*
        for (coupon in 0 until coupList.size) {
            val buttondynamic = Button(this)
            buttondynamic.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT

            )
            buttondynamic.text = coupList[coupon].label
            buttondynamic.tag = coupList[coupon].code
            buttondynamic.setOnClickListener()
            {
                val intent = Intent(this, CouponPage::class.java)
                intent.putExtra("Description", coupList[coupon].description)
                intent.putExtra("Title", coupList[coupon].title)
                intent.putExtra("CODE", coupList[coupon].code)
                intent.putExtra("Expiration", coupList[coupon].endDate.toString())


                startActivity(intent)
            }

        }
*/

    }

    private fun loadCoupons(_coupList : MutableList<Offer>, _intent : Intent, _button : Button) {
        //initiate the service
        val destinationService = APIBUILDER.buildService(CouponService::class.java)
        val requestCall = destinationService.getAffectedCouponList()
        //make network call asynchronously
        requestCall.enqueue(object : Callback<Coupons> {
            override fun onResponse(call: Call<Coupons>, response: Response<Coupons>) {
                if (response.isSuccessful) {
                    val couponList = response.body()!!
                    for (coupon in 0 until couponList.offers.size) {

                        _button.layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT

                        )
                        _button.text = couponList.offers[coupon].label
                        _button.tag = couponList.offers[coupon].code
                        _button.setOnClickListener()
                        {
                            _intent.putExtra("Description", couponList.offers[coupon].description)
                            _intent.putExtra("Title", couponList.offers[coupon].title)
                            _intent.putExtra("CODE", couponList.offers[coupon].code)
                            _intent.putExtra("Expiration", couponList.offers[coupon].endDate.toString())


                            startActivity(_intent)
                        }

                    }

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



