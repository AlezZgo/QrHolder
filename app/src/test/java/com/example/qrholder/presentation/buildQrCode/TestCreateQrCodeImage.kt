package com.example.qrholder.presentation.buildQrCode

import android.graphics.Bitmap

class TestCreateQrCodeImage : CreateQrCodeImage {

    var createQrCodeImageCalledList = mutableListOf<String>()
//todo
    override fun create(input: String): Bitmap? {
        createQrCodeImageCalledList.add(input)
        return null
    }

}
