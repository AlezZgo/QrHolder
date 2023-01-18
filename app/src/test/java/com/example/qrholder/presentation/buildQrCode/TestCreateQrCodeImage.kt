package com.example.qrholder.presentation.buildQrCode

import android.graphics.Bitmap

class TestCreateQrCodeImage : CreateQrCodeImage {

    var createQrCodeImageCalledList = mutableListOf<String>()

    override fun create(input: String): BitmapWrapper {
        createQrCodeImageCalledList.add(input)
        return TestBitmapWrapper()
    }

}


