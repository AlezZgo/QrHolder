package com.alezzgo.qrholder.presentation.scanFromGallery

sealed class ScanQrCodeResult {

    abstract fun show(
        onSuccess: (content: String) -> Unit,
        onError: (message: String) -> Unit,
    )

    data class Success(
        private val content: String
    ) : ScanQrCodeResult() {
        override fun show(
            onSuccess: (content: String) -> Unit,
            onError: (message: String) -> Unit
        ) = onSuccess.invoke(content)

    }

    data class Error(
        private val message: String
    ) : ScanQrCodeResult() {
        override fun show(
            onSuccess: (content: String) -> Unit,
            onError: (message: String) -> Unit
        ) = onError.invoke(message)

    }
}