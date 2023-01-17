package com.example.qrholder.presentation.buildQrCode

import android.graphics.Bitmap

class TestCreateQrCodeImage : CreateQrCodeImage {

    var createQrCodeImageCalledList = mutableListOf<String>()

    override fun create(input: String): Bitmap {
        createQrCodeImageCalledList.add(input)
        return Bitmap.createBitmap(4,4,Bitmap.Config.ARGB_8888)
    }

}
