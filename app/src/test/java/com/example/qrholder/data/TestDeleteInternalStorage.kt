package com.example.qrholder.data

import com.example.qrholder.presentation.buildQrCode.BitmapWrapper

class TestDeleteInternalStorage : DeleteInternalStorage{
    override fun deleteImage(path: String) = Unit
}