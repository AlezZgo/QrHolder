package com.example.qrholder.data

import android.content.Context
import android.graphics.Bitmap
import com.example.qrholder.domain.ImagePath
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject


interface SaveInternalStorage<T>  {

    fun save(model: T, name: String) : ImagePath

    class ImageSave @Inject constructor(
        @ApplicationContext private val appContext: Context
    ) : SaveInternalStorage<Bitmap> {
        override fun save(model: Bitmap, name: String) : ImagePath{
            val directory: File = appContext.getDir("imageDir", Context.MODE_PRIVATE)
            val path = File(directory, "$name.jpg")

            var fos: FileOutputStream? = null
            return try {
                fos = FileOutputStream(path)
                // Use the compress method on the BitMap object to write image to the OutputStream
                model.compress(Bitmap.CompressFormat.PNG, 100, fos)
                ImagePath.Success(path.path)
            } catch (e: Exception) {
                e.printStackTrace()
                ImagePath.Error(e.message?:"Something went wrong")
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
