package com.alezzgo.qrholder.presentation.buildQrCode

class TestCreateQrCodeImage : CreateQrCodeImage {

    var createQrCodeImageCalledList = mutableListOf<String>()

    override fun create(input: String): BitmapWrapper {
        createQrCodeImageCalledList.add(input)
        return TestBitmapWrapper()
    }

}


