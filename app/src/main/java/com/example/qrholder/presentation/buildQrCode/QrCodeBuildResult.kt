package com.example.qrholder.presentation.buildQrCode

import com.example.qrholder.presentation.home.model.QrCodeUi

sealed class QrCodeBuildResult  {

    abstract fun show(callback : (result : QrCodeBuildResult)-> Unit)

    data class Success(
        private val qrCode: QrCodeUi
    ) : QrCodeBuildResult() {
        override fun show(callback: (result: QrCodeBuildResult) -> Unit) {
            callback.invoke(this)
        }

    }

    data class Error(
        private val errorMessage: String
    ) : QrCodeBuildResult() {
        override fun show(callback: (result: QrCodeBuildResult) -> Unit) {
            callback.invoke(this)
        }

    }

}