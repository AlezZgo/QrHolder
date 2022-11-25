package com.example.qrholder.home.ui

sealed class HomeUiState {

    interface Mapper<T> {
        fun map(list: List<QrCodeUi> = emptyList(), errorMessage: String = ""): T
    }

    abstract fun <T> map(mapper: Mapper<T>): T

    object Loading : HomeUiState() {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map()
    }

    data class Success(
        private val qrCodes: List<QrCodeUi> = emptyList()
    ) : HomeUiState() {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(list = qrCodes)
    }

    object Empty : HomeUiState() {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map()
    }

    object NothingWasFound : HomeUiState() {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map()
    }

    data class Error(
        private val errorMessage: String
    ) : HomeUiState() {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(errorMessage = errorMessage)
    }

}
