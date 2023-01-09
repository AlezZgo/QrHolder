package com.example.qrholder.domain

import android.widget.ImageView
import com.example.qrholder.presentation.home.model.QrCodeUi

sealed class QrCodeBuildResult{

    abstract fun show(imageView : ImageView, callback : ()->Unit)

    data class Success(
        private val qrCodeUi: QrCodeUi
    ) : QrCodeBuildResult() {
        override fun show(imageView : ImageView,callback: () -> Unit) {
            qrCodeUi.mapToImage(imageView)
            callback.invoke()
        }
    }

    data class Error(
        private val error : String
    ) : QrCodeBuildResult() {
        override fun show(imageView : ImageView,callback: () -> Unit) {
            callback.invoke()
        }
    }

}