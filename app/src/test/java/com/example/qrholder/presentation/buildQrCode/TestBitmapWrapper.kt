package com.example.qrholder.presentation.buildQrCode

import android.graphics.Bitmap
import com.example.qrholder.data.QrCodeData
import com.example.qrholder.data.SaveQrCode
import com.example.qrholder.domain.model.ImagePath
import com.example.qrholder.domain.model.QrCode
import java.io.FileOutputStream

class TestBitmapWrapper : BitmapWrapper{

    override suspend fun save(
        titleText: String,
        contentText: String,
        saveQrCode: SaveQrCode,
        mapper: QrCode.Mapper<QrCodeData>
    ): QrCode {
        val path = saveQrCode.saveImage(this,titleText)
        return path.map(title = titleText, content = contentText)
    }

    override fun compress(
        compressFormat: Bitmap.CompressFormat,
        i: Int,
        fileOutputStream: FileOutputStream
    ) = Unit
}