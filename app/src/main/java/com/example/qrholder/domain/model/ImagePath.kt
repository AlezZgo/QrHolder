package com.example.qrholder.domain.model

import com.example.qrholder.presentation.buildQrCode.QrCodeBuildResult
import com.example.qrholder.presentation.home.model.QrCodeUi

sealed class ImagePath {

    abstract fun map(title: String, content: String): QrCodeBuildResult

    data class Success(
        private val path: String
    ) : ImagePath() {
        override fun map(title: String, content: String) =
            QrCodeBuildResult.Success(QrCodeUi(title, content, path))
    }

    data class Error(
        private val errorMessage: String
    ) : ImagePath() {
      override fun map(title: String, content: String)  = QrCodeBuildResult.Error(errorMessage)
    }
}