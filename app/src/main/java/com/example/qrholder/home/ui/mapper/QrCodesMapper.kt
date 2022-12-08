package com.example.qrholder.home.ui.mapper

import com.example.qrholder.home.domain.QrCode
import com.example.qrholder.home.domain.QrCodes
import com.example.qrholder.home.ui.HomeCommunications
import com.example.qrholder.home.ui.QrCodeUi
import com.example.qrholder.home.ui.QrCodeUiCompleteList

class QrCodesMapper(
    private val communications: HomeCommunications,
    private val qrCodeToUiMapper: QrCode.Mapper<QrCodeUi>
) : QrCodes.Mapper<Unit> {
    override fun map(list: List<QrCode>, errorMessage: String) {
        communications.changeCompleteList(
            if (errorMessage.isEmpty())
                QrCodeUiCompleteList.Success(list.map { it.map(qrCodeToUiMapper) })
            else
                QrCodeUiCompleteList.Error(errorMessage)
        )
    }
}