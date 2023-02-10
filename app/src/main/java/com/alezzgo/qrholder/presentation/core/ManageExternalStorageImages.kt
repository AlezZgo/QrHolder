package com.alezzgo.qrholder.presentation.core

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


interface ManageExternalStorageImages {

    fun allShownImagesPath(): List<String>

    class Base @Inject constructor(
        @ApplicationContext private val context: Context
    ) : ManageExternalStorageImages {

        override fun allShownImagesPath(): List<String> {
            val cursor: Cursor?
            val listOfAllImages = mutableListOf<String>()
            var absolutePathOfImage: String?
            val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            val projection = arrayOf(
                MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME
            )
            cursor = context.contentResolver.query(
                uri, projection, null,
                null, null
            )
            val columnIndexData: Int = cursor!!.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
            cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
            while (cursor.moveToNext()) {
                absolutePathOfImage = cursor.getString(columnIndexData)
                listOfAllImages.add(absolutePathOfImage)
            }
            //need?
            cursor.close()
            return listOfAllImages
        }


    }


}
