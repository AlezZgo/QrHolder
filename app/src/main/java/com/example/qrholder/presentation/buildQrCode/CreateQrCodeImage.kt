package com.example.qrholder.presentation.buildQrCode

import android.graphics.Bitmap
import android.graphics.Color
import com.example.qrholder.di.QrCodeStandardSize
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import java.util.*
import javax.inject.Inject


interface CreateQrCodeImage : Create<String,Bitmap> {

    class Base @Inject constructor(
        @QrCodeStandardSize private var width: Int,
        @QrCodeStandardSize private var height: Int,
    ) : CreateQrCodeImage {

        override fun create(input : String) : Bitmap {

            val bitMatrix = MultiFormatWriter().encode(input, BarcodeFormat.QR_CODE, 150, 150,null)

            val w: Int = bitMatrix.width
            val h: Int = bitMatrix.height
            val pixels = IntArray(w * h)

            for (y in 0 until h) {
                val offset = y * w
                for (x in 0 until w) {
                    pixels[offset + x] = if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE
                }
            }

            val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
            bitmap.setPixels(pixels, 0, width, 0, 0, w, h)

            return bitmap
            }

        }
}
