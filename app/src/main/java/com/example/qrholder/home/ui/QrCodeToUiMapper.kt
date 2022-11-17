package com.example.qrholder.home.ui

import com.example.qrholder.home.domain.QrCode

class QrCodeToUiMapper : QrCode.Mapper<QrCodeUi> {
    override fun map(title: String, content: String): QrCodeUi =
        QrCodeUi(title = title, content = content)

}