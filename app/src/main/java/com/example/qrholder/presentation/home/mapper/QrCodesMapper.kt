package com.example.qrholder.presentation.home.mapper

import com.example.qrholder.domain.model.QrCode
import com.example.qrholder.domain.model.QrCodes
import com.example.qrholder.presentation.home.HomeCommunications
import com.example.qrholder.presentation.home.model.QrCodeUi
import com.example.qrholder.presentation.home.model.QrCodeCompleteListUi
import javax.inject.Inject

class QrCodesMapper @Inject constructor(
    private val communications: HomeCommunications,
    private val qrCodeToUiMapper: QrCode.Mapper<QrCodeUi>
) : QrCodes.Mapper<Unit> {
    override fun map(list: List<QrCode>, errorMessage: String) {
        communications.changeCompleteList(
            if (errorMessage.isEmpty())
                QrCodeCompleteListUi.Success(list.map { it.map(qrCodeToUiMapper) })
            else
                QrCodeCompleteListUi.Error(errorMessage)
        )
    }
}