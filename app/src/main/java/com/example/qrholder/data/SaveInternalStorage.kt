package com.example.qrholder.data

import android.content.Context
import android.graphics.Bitmap
import com.example.qrholder.R
import com.example.qrholder.core.ManageResources
import com.example.qrholder.domain.model.ImagePath
import com.example.qrholder.presentation.buildQrCode.BitmapWrapper
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject


interface SaveInternalStorage<T>  {

    fun save(model: T, name: String) : ImagePath

    class ImageSave @Inject constructor(
        @ApplicationContext private val appContext: Context,
        private val manageResources: ManageResources,
    ) : SaveInternalStorage<BitmapWrapper> {
        override fun save(model: BitmapWrapper, name: String) : ImagePath {
            val directory: File = appContext.getDir("imageDir", Context.MODE_PRIVATE)
            val path = File(directory, "$name.jpg")

            var fos: FileOutputStream? = null
            return try {
                fos = FileOutputStream(path)
                model.compress(Bitmap.CompressFormat.PNG, 100, fos)
                ImagePath(path.path)
            } catch (e: Exception) {
                e.printStackTrace()
                throw Exception(e.message?:manageResources.string(R.string.unable_to_save_image_to_internal_storage))
            } finally {
                try {
                    fos?.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

        }
    }

}
