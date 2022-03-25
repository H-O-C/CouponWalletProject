package com.example.mylogin

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.io.InputStreamReader

fun reading(csv_file : String, coupList : MutableList<Coupons>, catList : MutableList<String>)
{
    var readers: BufferedReader? = null
    var line: String

    try {
        readers = BufferedReader(FileReader(csv_file))
        while (readers.readLine().also { line = it } != null) {
            var number = 0
            val values = line.split(",").toTypedArray()
            val row: List<String> = line.split(",")
            for (index in values) {
                val coupon = Coupons()
                coupon.setCoupons(
                    row[0], row[1], row[18], row[6], row[12],row[5]
                )
                //due to bug it will duplicate it 10 times over, with this it does not
                number += 1
                if (number % 10 == 0) {
                    catList.add(row[8])
                    coupList.add(coupon)
                }
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
    } finally {
        try {
            readers!!.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }



}
fun reading2(minput : InputStreamReader, coupList : MutableList<Coupons>, catList : MutableList<String>, coupCat : MutableList<CouponCategory>)
{
    var line: String?
    val reader = BufferedReader(minput)
    var displayData = ""
    while (reader.readLine().also { line = it } != null) {
        val row: List<String> = line!!.split(",")
        var numberz = 0
        var number = 0
        //Creates a row system
        displayData =
            displayData + row[0] + "\t" + row[1] + "\t" + row[2] + "\t" + row[3] + "\t" + row[4] + "\t" + row[5] + "\t" + row[6] + "\t" + row[7] + "\t" + row[8] + "\t" + row[9] + "\t" + row[10] + "\t" + row[11] + "\t" + row[12] + "\t" + row[13] + "\t" + row[14] + "\t" + row[15] + "\t" + row[16] + "\t" + row[17] + "\n"
        val values = line!!.split(",").toTypedArray()
        for (index in values) {
//Rows changed to input into the list.
            val coupon = Coupons()
            coupon.setCoupon(
                row[0],
                row[1],
                row[2],
                row[3],
                row[4],
                row[5],
                row[6],
                row[7],
                row[8],
                row[9],
                row[10],
                row[11],
                row[12], row[13], row[14], row[15], row[16], row[17]
            )
            //due to bug it will duplicate it 10 times over, with this it does not
            numberz += 1
            if (numberz % 10 == 0) {
                catList.add(row[8])
                coupList.add(coupon)
            }




        }


    }

}
fun AdapterList(coupList : MutableList<Coupons>, catList : MutableList<String>, coupCat : MutableList<CouponCategory>)
{

    var cates = 0
    while (cates in 0 until catList.size )
    {
        val couponcat = CouponCategory()
        val selectedSeries = coupList.toMutableList()
        val Categorys = catList.find { actor -> catList[cates].equals(actor) }
        var coups = 0
        while (coups in 0 until selectedSeries.size)
        {

            var coupListd: MutableList<Coupons> = mutableListOf()
            if (!(assertEquals(Categorys, selectedSeries[coups].cate)))
            {
                selectedSeries.remove(selectedSeries[coups])

            }

        }

    }
    coupList.clear()
    catList.clear()

}

fun assertEquals(categorys: String?, cate: String?): Boolean {

    if (categorys == cate)
        return true
    return false
}

class Categories : AppCompatActivity() {
    var mainCategoryRecycler: RecyclerView? = null
    var mainRecyclerAdapter: CategoryAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
        var coupList: MutableList<Coupons> = mutableListOf()
        var coupList2: MutableList<Coupons> = mutableListOf()
        var catList: MutableList<String> = mutableListOf()
        val fcoupList: MutableList<CouponCategory> = mutableListOf()

        val minput = InputStreamReader(assets.open("tessts.csv"))
        val apkey = "9da784d3351027e3709ea42d8760a92f" //The API KEY
        val incremental = true

        //$last_extract_datetime = ; // Ideally leave this blank. Populate only if you want to override last extract time stored in the system
        val format = "csv"
        val off_record =
            false // If set to true, system will not update your last extract time. This is useful for debugging purpose. Please note, this will still count in your API Usage limit

        //This calls the API
        //var last_extract = (empty(valast_extract_datetime) ? '' : strtotime($last_extract_datetime) )
        val csv_file =
            "https://couponapi.org/api/getFeed/?API_KEY=" + apkey + "&incremental=" + incremental + "format=" + format + "off_record=" + off_record

        //reading(csv_file,coupList,catList)
        reading2(minput,coupList,catList,fcoupList)
        catList = catList.distinct() as MutableList<String>
        coupList = coupList.distinct() as MutableList<Coupons>

        catList.remove("Category")
        catList.remove("")
        coupList.removeAt(0)
        catList.size
        coupList.size
        //AdapterList(coupList,catList,fcoupList)

        val distinct = catList.distinct().toList()
        val rl = RelativeLayout(this)
        val llmain = LinearLayout(this)

        val number = 1;


        if(number == 1) {
            for (category in distinct.indices) {
                rl.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )

                val llmain2 = LinearLayout(this)

                llmain.layoutParams =
                    ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )

                llmain.orientation = LinearLayout.HORIZONTAL

                val categoryslider = HorizontalScrollView(this)
                categoryslider.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )


                for (coupon in 0 until coupList.size) {
                    val buttondynamic = Button(this)
                    buttondynamic.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT

                    )
                    llmain2.setLayoutParams(
                        ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                    )
                    llmain2.setOrientation(LinearLayout.HORIZONTAL)
                    buttondynamic.text = coupList[coupon].getValues()
                    buttondynamic.tag = coupList[coupon].id
                    buttondynamic.setOnClickListener()
                    {
                        val intent = Intent(this, CouponPage::class.java)
                        intent.putExtra("Description", coupList[coupon].desc)
                        intent.putExtra("Expiration", coupList[coupon].enddate)
                        intent.putExtra("Title", coupList[coupon].title)
                        intent.putExtra("CODE", coupList[coupon].code)
                        intent.putExtra("Category", coupList[coupon].cate)
                        intent.putExtra("Store", coupList[coupon].store)
                        intent.putExtra("ID", coupList[coupon].id)
                        intent.putExtra("value", coupList[coupon].values)


                        startActivity(intent)
                    }
                    if (coupList[coupon].cate == distinct[category]) {
                        llmain2.addView(buttondynamic)
                    }
                }
                llmain2.tag = category
                llmain.tag = category + 1
                categoryslider.tag = distinct[category]
                categoryslider.addView(llmain2)
                val cateName = TextView(this)
                cateName.setLayoutParams(
                    ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                )
                cateName.text = distinct[category]
                llmain.addView(cateName)
                llmain.addView(categoryslider, category)
            }
            rl.addView(llmain)


            this.setContentView(rl)


        }
/*
        var cates = 0
        catList = catList.distinct() as MutableList<String>
        catList.removeAt(0)
        catList.removeAt(6)
        while (cates in 0 until catList.size )
        {
            var coups = 0
            while (coups in 0 until coupList.size)
            {
                val couponcat = CouponCategory()
                if (catList[cates] != null)
                {
                    couponcat.categoryTitle = catList[cates]
                }


                if (catList[cates].toString() == coupList[coups].getCate().toString())
                {
                    if(coupList[coups].cate != null)
                    {
                        couponcat.categoryItemList?.add(coupList[coups])

                    }
                }

            }

        }

 */

        /*
        for (coupon in 0 until coupList.size) {
            val buttondynamic = Button(this)
            buttondynamic.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT

            )
            llmain2.setLayoutParams(
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            )
            llmain2.setOrientation(LinearLayout.HORIZONTAL)
            buttondynamic.text = coupList[coupon].getValues()
            buttondynamic.tag = coupList[coupon].id
            buttondynamic.setOnClickListener()
            {
                val intent = Intent(this, CouponPage::class.java)
                intent.putExtra("Description", coupList[coupon].desc)
                intent.putExtra("Expiration", coupList[coupon].enddate)
                intent.putExtra("Title", coupList[coupon].title)
                intent.putExtra("CODE", coupList[coupon].code)
                intent.putExtra("Category", coupList[coupon].cate)
                intent.putExtra("Store", coupList[coupon].store)
                intent.putExtra("ID", coupList[coupon].id)
                intent.putExtra("value", coupList[coupon].values)


                startActivity(intent)
            }
            if (coupList[coupon].cate == distinct[category]) {
                llmain2.addView(buttondynamic)
            }
        }

         */


    }
    private fun setMainCategoryRecycler(allCategoryList: MutableList<CouponCategory>) {
        mainCategoryRecycler = findViewById<RecyclerView>(R.id.coupon_recycler)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        mainCategoryRecycler?.layoutManager = layoutManager
        mainRecyclerAdapter = CategoryAdapter(this, allCategoryList)
        mainCategoryRecycler?.adapter = mainRecyclerAdapter
    }



}
