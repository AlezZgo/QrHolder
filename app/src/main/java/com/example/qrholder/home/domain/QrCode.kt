package com.example.qrholder.home.domain

data class QrCode(
    private val title: String,
    private val content: String,
) {

    interface Mapper<T> {
        fun map(title: String, content: String): T
    }

    fun <T> map(mapper: Mapper<T>): T = mapper.map(title = title, content = content)

}

