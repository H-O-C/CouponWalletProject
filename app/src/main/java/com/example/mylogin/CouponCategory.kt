package com.example.mylogin

class CouponCategory {
    var categoryTitle: String? = null
    var categoryItemList: MutableList<Coupons>? = mutableListOf()

    fun AllCategory(categoryTitle: String?, categoryItemList: MutableList<Coupons>?) {
        this.categoryTitle = categoryTitle
        this.categoryItemList = categoryItemList
    }
}