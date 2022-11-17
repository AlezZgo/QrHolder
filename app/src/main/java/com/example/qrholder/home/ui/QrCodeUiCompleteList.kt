package com.example.qrholder.home.ui

import com.example.qrholder.core.Communication

sealed class QrCodeUiCompleteList {

    interface Mapper<T>{
        fun map(
            completeList: List<QrCodeUi>,
            errorMessage: String,
            filter: String,
            uiState: Communication.Mutable<HomeUiState>
        ) : T
    }

    abstract fun <T> map(mapper: Mapper<T>,filter: String, uiState: Communication.Mutable<HomeUiState>) : T

    data class Success(
        private val qrCodes : List<QrCodeUi>
    ) : QrCodeUiCompleteList() {

        override fun <T> map(
            mapper: Mapper<T>,
            filter: String,
            uiState: Communication.Mutable<HomeUiState>
        ): T = mapper.map(qrCodes,"",filter,uiState)
    }

    data class Error(
        private val message : String
    ): QrCodeUiCompleteList() {

        override fun <T> map(
            mapper: Mapper<T>,
            filter: String,
            uiState: Communication.Mutable<HomeUiState>
        ): T = mapper.map(emptyList(),message,filter,uiState)
    }
}
