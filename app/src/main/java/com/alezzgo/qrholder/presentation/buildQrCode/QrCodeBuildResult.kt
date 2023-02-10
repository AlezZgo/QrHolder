package com.alezzgo.qrholder.presentation.buildQrCode

import com.alezzgo.qrholder.presentation.core.model.QrCodeUi

sealed class QrCodeBuildResult {

    abstract fun show(
        successBuildAction: (result: QrCodeUi) -> Unit,
        errorBuildAction: (errorMessage: String) -> Unit
    )

    data class Success(
        private val qrCode: QrCodeUi
    ) : QrCodeBuildResult() {
        override fun show(
            successBuildAction: (result: QrCodeUi) -> Unit,
            errorBuildAction: (errorMessage: String) -> Unit
        ) {
            successBuildAction.invoke(qrCode)
        }

    }

    data class Error(
        private val errorMessage: String
    ) : QrCodeBuildResult() {
        override fun show(
            successBuildAction: (result: QrCodeUi) -> Unit,
            errorBuildAction: (errorMessage: String) -> Unit
        ) {
            errorBuildAction.invoke(errorMessage)
        }

    }

}