package com.alezzgo.qrholder.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface DeleteInternalStorage : DeleteQrCodeImage {

    class Base @Inject constructor(
        @ApplicationContext private val appContext: Context,
    ) : DeleteInternalStorage {

        override fun deleteImage(path: String) {
            appContext.deleteFile(path)
        }

    }
}

interface DeleteQrCodeImage {
    fun deleteImage(path: String)
}