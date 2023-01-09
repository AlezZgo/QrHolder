package com.example.qrholder.domain

import com.example.qrholder.presentation.home.model.QrCodeUi

sealed class QrCodeBuildResult{

    abstract fun show(callback : ()->Unit)

    data class Success(
        private val qrCodeUi: QrCodeUi
    ) : QrCodeBuildResult() {
        override fun show(callback: () -> Unit) = callback.invoke()
    }

    data class Error(
        private val error : String
    ) : QrCodeBuildResult() {
        override fun show(callback: () -> Unit) = callback.invoke()
    }

}