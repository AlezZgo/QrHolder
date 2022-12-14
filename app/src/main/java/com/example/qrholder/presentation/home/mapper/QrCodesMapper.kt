package com.example.qrholder.presentation.home.mapper

import com.example.qrholder.domain.QrCode
import com.example.qrholder.domain.QrCodes
import com.example.qrholder.presentation.home.HomeCommunications
import com.example.qrholder.presentation.home.QrCodeUi
import com.example.qrholder.presentation.home.QrCodeUiCompleteList
import javax.inject.Inject

class QrCodesMapper @Inject constructor(
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