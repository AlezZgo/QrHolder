package com.example.qrholder.home.ui

import com.example.qrholder.home.domain.QrCode
import com.example.qrholder.home.domain.QrCodes

class FetchAllResultMapper(
    private val communications: HomeCommunications,
    private val qrCodeToUiMapper: QrCode.Mapper<QrCodeUi>
) : QrCodes.Mapper<Unit> {
    override fun map(list: List<QrCode>, errorMessage: String) {
        if (errorMessage.isEmpty()) {
            if (list.isEmpty())
                communications.showState(HomeUiState.Empty)
            else
                communications.changeCompleteList(list.map { it.map(qrCodeToUiMapper) })
        } else
            communications.showState(HomeUiState.Error(errorMessage))
    }
}