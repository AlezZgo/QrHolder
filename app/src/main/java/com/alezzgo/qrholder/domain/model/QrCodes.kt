package com.alezzgo.qrholder.domain.model

sealed class QrCodes {

    interface Mapper<T> {
        fun map(list: List<QrCode>, errorMessage: String): T
    }

    abstract fun <T> map(mapper: Mapper<T>): T

    data class Success(
        private val qrCodes: List<QrCode> = emptyList()
    ) : QrCodes() {
        override fun <T> map(mapper: Mapper<T>) = mapper.map(qrCodes, "")
    }

    data class Failure(
        private val message: String
    ) : QrCodes() {
        override fun <T> map(mapper: Mapper<T>) = mapper.map(emptyList(), message)
    }
}
