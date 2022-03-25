package com.example.mylogin

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter


class CouponPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coupon_page)

        /*
        intent.putExtra("Description", coupList[coupon].desc)
        intent.putExtra("Expiration", coupList[coupon].enddate)
        intent.putExtra("Title", coupList[coupon].title)
        intent.putExtra("CODE", coupList[coupon].code)
        intent.putExtra("Category", coupList[coupon].cate)
        intent.putExtra("Store", coupList[coupon].store)
        intent.putExtra("ID", coupList[coupon].id)
        intent.putExtra("value", coupList[coupon].values)
        */

        val couponName = findViewById<TextView>(R.id.txtCoupon)
        couponName.text = "Coupon ID: " +intent.getStringExtra("ID")
        val expiration = findViewById<TextView>(R.id.textExp)
        expiration.text = "Exp date: " + intent.getStringExtra("Expiration")
        val desc = findViewById<TextView>(R.id.textDesc)
        desc.text =  intent.getStringExtra("value")+ " for " + intent.getStringExtra("Category") + " at "+intent.getStringExtra("Store")
        val barCode = findViewById<ImageView>(R.id.barCode)
        barCode.setImageBitmap(intent.getStringExtra("CODE")?.let { generateBarCode(it) })
        val switchs = findViewById<Switch>(R.id.switch2)
        switchs.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked)  barCode.setImageBitmap(intent.getStringExtra("CODE")?.let { getQrCodeBitmap(it) })
            else barCode.setImageBitmap(intent.getStringExtra("CODE")?.let { generateBarCode(it) })
        }

        val cateButton = findViewById<Button>(R.id.saveButton)
        cateButton.setOnClickListener{
            val intents = Intent(this, MainActivity::class.java)
            intents.putExtra("Description", intent.getStringExtra("Description"))
            intents.putExtra("Expiration", intent.getStringExtra("Expiration"))
            intents.putExtra("Title", intent.getStringExtra("Title"))
            intents.putExtra("CODE", intent.getStringExtra("CODE"))
            intents.putExtra("Category", intent.getStringExtra("Category"))
            intents.putExtra("Store", intent.getStringExtra("Store"))
            intents.putExtra("ID", intent.getStringExtra("ID"))
            intents.putExtra("value",  intent.getStringExtra("value"))


            couponName.animate().apply{duration = 2000
                rotationXBy(-720f)}.start()

            /*
            barCode.animate().apply{duration = 2000
            rotationYBy(720f)}.start()
            expiration.animate().apply{duration = 2000
                rotationXBy(-720f)}.start()
            desc.animate().apply{duration = 2000
                rotationXBy(-720f)}.start()
            switchs.animate().apply{duration = 2000
                rotationXBy(-720f)}.start()


             */
            val text = "Coupon Saved!"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
        }







    }
    fun generateBarCode(text: String): Bitmap {
        val width = 500
        val height = 150
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val codeWriter = MultiFormatWriter()
        try {
            val bitMatrix = codeWriter.encode(
                text,
                BarcodeFormat.CODE_93,
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

    fun getQrCodeBitmap(text: String): Bitmap {
        val size = 512
        val qrCodeContent = text
        val hints = hashMapOf<EncodeHintType, Int>().also { it[EncodeHintType.MARGIN] = 1 }
        val bits = QRCodeWriter().encode(qrCodeContent, BarcodeFormat.QR_CODE, size, size)
        return Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565).also {
            for (x in 0 until size) {
                for (y in 0 until size) {
                    it.setPixel(x, y, if (bits[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        }
    }



}