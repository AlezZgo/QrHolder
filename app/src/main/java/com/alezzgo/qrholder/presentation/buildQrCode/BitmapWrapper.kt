package com.alezzgo.qrholder.presentation.buildQrCode

import android.graphics.Bitmap
import com.alezzgo.qrholder.data.QrCodeData
import com.alezzgo.qrholder.data.SaveQrCode
import com.alezzgo.qrholder.domain.model.QrCode
import java.io.FileOutputStream

interface BitmapWrapper {

    suspend fun save(
        titleText: String,
        contentText: String,
        saveQrCode: SaveQrCode,
        mapper: QrCode.Mapper<QrCodeData>
    ): QrCode

    fun compress(compressFormat: Bitmap.CompressFormat, i: Int, fileOutputStream: FileOutputStream)

    class Base(private val bitmap: Bitmap?) : BitmapWrapper {
        override suspend fun save(
            titleText: String,
            contentText: String,
            saveQrCode: SaveQrCode,
            mapper: QrCode.Mapper<QrCodeData>
        ): QrCode {
            val imagePath = saveQrCode.saveImage(this, titleText)
            val qrCode = imagePath.map(titleText, contentText)
            saveQrCode.save(qrCode.map(mapper))
            return qrCode
        }

        override fun compress(
            compressFormat: Bitmap.CompressFormat,
            i: Int,
            fileOutputStream: FileOutputStream
        ) {
            bitmap?.compress(compressFormat, i, fileOutputStream)
        }

    }

}
