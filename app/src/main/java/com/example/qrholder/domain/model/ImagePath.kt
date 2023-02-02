package com.example.qrholder.domain.model

data class ImagePath(
    private val path: String
) {
    //todo fix
    fun map(title: String, content: String) =
        QrCode(title, content, path)
}

