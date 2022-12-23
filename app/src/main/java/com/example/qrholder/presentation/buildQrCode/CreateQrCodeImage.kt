package com.example.qrholder.presentation.buildQrCode

import android.graphics.Bitmap
import android.graphics.Color
import com.example.qrholder.di.QrCodeStandardSize
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import javax.inject.Inject


interface CreateQrCodeImage : Create<String, BitmapWrapper> {

    class Base @Inject constructor(
        @QrCodeStandardSize private var qrCodeWidth: Int,
    ) : CreateQrCodeImage {

        override fun create(input: String): BitmapWrapper {

            val bitMatrix = MultiFormatWriter().encode(
                input,
                BarcodeFormat.QR_CODE,
                qrCodeWidth,
                qrCodeWidth,
                null
            )

            val width: Int = bitMatrix.width
            val height: Int = bitMatrix.height
            val pixels = IntArray(width * height)

            for (y in 0 until height) {
                val offset = y * width
                for (x in 0 until width) {
                    pixels[offset + x] = if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE
                }
            }

            return BitmapWrapper.Base(
                Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
                    .apply { setPixels(pixels, 0, qrCodeWidth, 0, 0, width, height) }
            )
        }
    }
}
