package com.alezzgo.qrholder.data

data class QrCodeData(
    private val title: String,
    private val content: String,
    private val path: String,
) {

    interface Mapper<T> {
        fun map(title: String, content: String, path: String): T
    }

    fun <T> map(mapper: Mapper<T>): T = mapper.map(title = title, content = content, path = path)
}
