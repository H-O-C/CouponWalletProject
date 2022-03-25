package com.example.mylogin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class CouponAdapter(
    private val context: Context,
    categoryItemList: MutableList<Coupons>
) :
    RecyclerView.Adapter<CouponAdapter.CategoryItemViewHolder>() {
    private val categoryItemList: MutableList<Coupons> = categoryItemList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        return CategoryItemViewHolder(
            LayoutInflater.from(context).inflate(R.layout.coupon_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return categoryItemList.size
    }

    class CategoryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var buttons: Button = itemView.findViewById(R.id.cateButton)

    }

}