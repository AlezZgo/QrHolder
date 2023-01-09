package com.example.qrholder.presentation.buildQrCode

import android.graphics.Bitmap
import android.graphics.Color
import com.example.qrholder.di.EmptyText
import com.example.qrholder.di.QrCodeStandardSize
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import java.util.*
import javax.inject.Inject



interface CreateQrCodeImage : Create<String,Bitmap> {

    class Base @Inject constructor(
        @QrCodeStandardSize private var width: Int,
        @QrCodeStandardSize private var height: Int,
    ) : CreateQrCodeImage {

        override fun create(input : String) : Bitmap {

                val bitMatrix = QRCodeWriter()
                    .encode(input, BarcodeFormat.QR_CODE, width, height)

                val imageBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

                for (i in 0 until width) {
                    val column = IntArray(height)
                    Arrays.fill(column, if (bitMatrix.get(i, 0)) Color.BLACK else Color.WHITE)
                    imageBitmap.setPixels(column, 0, 1, i, 0, 1, height)
                }

                return imageBitmap
            }

        }
}
