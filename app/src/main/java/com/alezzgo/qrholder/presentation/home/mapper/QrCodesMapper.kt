package com.alezzgo.qrholder.presentation.home.mapper

import com.alezzgo.qrholder.domain.model.QrCode
import com.alezzgo.qrholder.domain.model.QrCodes
import com.alezzgo.qrholder.presentation.core.model.QrCodeUi
import com.alezzgo.qrholder.presentation.home.HomeCommunications
import com.alezzgo.qrholder.presentation.home.model.QrCodeCompleteListUi
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