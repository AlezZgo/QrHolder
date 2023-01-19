package com.example.qrholder.presentation.buildQrCode

import android.graphics.Bitmap
import com.example.qrholder.data.SaveQrCode
import com.example.qrholder.domain.model.ImagePath
import java.io.FileOutputStream

class TestBitmapWrapper : BitmapWrapper{
    override suspend fun save(titleText: String, saveQrCode: SaveQrCode): ImagePath {
        return saveQrCode.saveImage(this,titleText)
    }

    override fun compress(
        compressFormat: Bitmap.CompressFormat,
        i: Int,
        fileOutputStream: FileOutputStream
    ) = Unit
}