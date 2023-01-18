package com.example.qrholder.presentation.buildQrCode

import android.graphics.Bitmap
import com.example.qrholder.data.SaveQrCodeImage
import com.example.qrholder.domain.model.ImagePath
import java.io.FileOutputStream

class TestBitmapWrapper : BitmapWrapper{
    override suspend fun save(titleText: String, saveQrCodeImage: SaveQrCodeImage): ImagePath {
        return saveQrCodeImage.saveQrCodeImage(this,titleText)
    }

    override fun compress(
        compressFormat: Bitmap.CompressFormat,
        i: Int,
        fileOutputStream: FileOutputStream
    ) = Unit
}