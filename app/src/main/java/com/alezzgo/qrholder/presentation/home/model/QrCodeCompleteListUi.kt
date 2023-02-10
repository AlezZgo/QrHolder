package com.alezzgo.qrholder.presentation.home.model

import com.alezzgo.qrholder.presentation.core.model.QrCodeUi
import com.alezzgo.qrholder.presentation.core.viewmodel.Communication

sealed class QrCodeCompleteListUi {

    interface Mapper<T> {
        fun map(
            completeList: List<QrCodeUi>,
            errorMessage: String,
            filter: String,
            uiState: Communication.Mutate<HomeUi>
        ): T
    }

    abstract fun <T> map(
        mapper: Mapper<T>,
        filter: String,
        uiState: Communication.Mutable<HomeUi>
    ): T

    data class Success(
        private val qrCodes: List<QrCodeUi>
    ) : QrCodeCompleteListUi() {

        override fun <T> map(
            mapper: Mapper<T>,
            filter: String,
            uiState: Communication.Mutable<HomeUi>
        ): T = mapper.map(qrCodes, "", filter, uiState)
    }

    data class Error(
        private val message: String
    ) : QrCodeCompleteListUi() {

        override fun <T> map(
            mapper: Mapper<T>,
            filter: String,
            uiState: Communication.Mutable<HomeUi>
        ): T = mapper.map(emptyList(), message, filter, uiState)
    }
}
