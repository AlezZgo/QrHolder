package com.alezzgo.qrholder.presentation.home.mapper

import com.alezzgo.qrholder.domain.model.QrCode
import com.alezzgo.qrholder.presentation.core.model.QrCodeUi
import javax.inject.Inject

class QrCodeToUiMapper @Inject constructor() : QrCode.Mapper<QrCodeUi> {
    override fun map(title: String, content: String, path: String): QrCodeUi =
        QrCodeUi(title = title, content = content, path = path)

}