package com.example.qrholder.presentation.buildQrCode

import com.example.qrholder.presentation.home.model.QrCodeUi

sealed class QrCodeBuildResult {

    abstract fun show(
        successBuildAction: (result: QrCodeBuildResult) -> Unit,
        errorBuildAction: (errorMessage: String) -> Unit
    )

    data class Success(
        private val qrCode: QrCodeUi
    ) : QrCodeBuildResult() {
        override fun show(
            successBuildAction: (result: QrCodeBuildResult) -> Unit,
            errorBuildAction: (errorMessage: String) -> Unit
        ) {
            successBuildAction.invoke(this)
        }

    }

    data class Error(
        private val errorMessage: String
    ) : QrCodeBuildResult() {
        override fun show(
            successBuildAction: (result: QrCodeBuildResult) -> Unit,
            errorBuildAction: (errorMessage: String) -> Unit
        ) {
            errorBuildAction.invoke(errorMessage)
        }

    }

}