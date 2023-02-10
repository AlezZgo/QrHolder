package com.alezzgo.qrholder.presentation.buildQrCode

import android.graphics.Bitmap
import com.alezzgo.qrholder.data.QrCodeData
import com.alezzgo.qrholder.data.SaveQrCode
import com.alezzgo.qrholder.domain.model.QrCode
import java.io.FileOutputStream

class TestBitmapWrapper : BitmapWrapper {

    override suspend fun save(
        titleText: String,
        contentText: String,
        saveQrCode: SaveQrCode,
        mapper: QrCode.Mapper<QrCodeData>
    ): QrCode {
        val path = saveQrCode.saveImage(this, titleText)
        return path.map(title = titleText, content = contentText)
    }

    override fun compress(
        compressFormat: Bitmap.CompressFormat,
        i: Int,
        fileOutputStream: FileOutputStream
    ) = Unit
}