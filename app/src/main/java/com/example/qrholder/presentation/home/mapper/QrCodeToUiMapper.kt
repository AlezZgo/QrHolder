package com.example.qrholder.presentation.home.mapper

import com.example.qrholder.domain.QrCode
import com.example.qrholder.presentation.home.QrCodeUi
import javax.inject.Inject

class QrCodeToUiMapper @Inject constructor() : QrCode.Mapper<QrCodeUi> {
    override fun map(title: String, content: String, path: String): QrCodeUi =
        QrCodeUi(title = title, content = content, path = path)

}