package com.example.mylogin

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException


class CouponPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coupon_page)

        val couponName = findViewById<TextView>(R.id.txtCoupon)
        couponName.text = intent.getStringExtra("Title")
        val expiration = findViewById<TextView>(R.id.textExp)
        expiration.text = intent.getStringExtra("Expiration")
        val desc = findViewById<TextView>(R.id.textDesc)
        desc.text =  intent.getStringExtra("Description")
        val barCode = findViewById<ImageView>(R.id.barCode)
        barCode.setImageBitmap(intent.getStringExtra("CODE")?.let { generateBarCode(it) })
    }
    fun generateBarCode(text: String): Bitmap {
        val width = 500
        val height = 150
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val codeWriter = MultiFormatWriter()
        try {
            val bitMatrix = codeWriter.encode(
                text,
                BarcodeFormat.CODE_128,
                width,
                height
            )
            for (x in 0 until width) {
                for (y in 0 until height) {
                    val color = if (bitMatrix[x, y]) Color.BLACK else Color.WHITE
                    bitmap.setPixel(x, y, color)
                }
            }
        } catch (e: WriterException) {
            Log.d("TAG", "generateBarCode: ${e.message}")
        }
        return bitmap
    }



}