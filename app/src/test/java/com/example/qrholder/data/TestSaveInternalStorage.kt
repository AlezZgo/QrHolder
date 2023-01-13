package com.example.qrholder.data

import android.graphics.Bitmap
import com.example.qrholder.domain.model.ImagePath

class TestSaveInternalStorage : SaveInternalStorage<Bitmap> {

    var expectedResult = ImagePath.Error("Nothing to save")

    override fun save(model: Bitmap, name: String) = expectedResult
}