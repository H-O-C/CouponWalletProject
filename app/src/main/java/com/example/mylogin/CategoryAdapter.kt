package com.example.mylogin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(private val context: Context, allCategoryList: MutableList<CouponCategory>) :
    RecyclerView.Adapter<CategoryAdapter.MainViewHolder>() {
    private val allCategoryList: MutableList<CouponCategory> = allCategoryList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.category_recycler, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.categoryTitle.text = allCategoryList[position].categoryTitle
        allCategoryList[position].categoryItemList?.let {
            setCatItemRecycler(holder.itemRecycler,
                it
            )
        }
    }

    override fun getItemCount(): Int {
        return allCategoryList.size
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryTitle: TextView = itemView.findViewById(R.id.cat_title)
        var itemRecycler: RecyclerView = itemView.findViewById(R.id.item_recycler)

    }

    private fun setCatItemRecycler(
        recyclerView: RecyclerView,
        categoryItemList: MutableList<Coupons>
    ) {
        val itemRecyclerAdapter = CouponAdapter(context, categoryItemList)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = itemRecyclerAdapter
    }

}