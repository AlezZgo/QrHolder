package com.example.qrholder.home.data

data class QrCodeData(
    private val title: String,
    private val content: String,
) {

    interface Mapper<T> {
        fun map(title: String, content: String): T
    }

    fun <T> map(mapper: Mapper<T>): T = mapper.map(title = title, content = content)
}
