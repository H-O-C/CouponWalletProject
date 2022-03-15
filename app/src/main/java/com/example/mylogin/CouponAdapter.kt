package com.example.mylogin

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CouponAdapter(private val couponsList: List<CouponsJson>) : RecyclerView.Adapter<CouponAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view  = LayoutInflater.from(parent.context).inflate(R.layout.coupon_item,parent,false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {

        return couponsList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("coupons", "List Count :${couponsList.size} ")


        return holder.bind(couponsList[position])

    }
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {


        var tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        var tvCases = itemView.findViewById<TextView>(R.id.tvCases)
        fun bind(coupons: CouponsJson) {

            val name ="ID :${coupons.offer_id.toString()}"
            tvTitle.text = coupons.title
            tvCases.text = name
        }

    }
}