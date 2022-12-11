package com.example.qrholder.home.ui.mapper

import com.example.qrholder.home.domain.QrCode
import com.example.qrholder.home.ui.QrCodeUi

class QrCodeToUiMapper : QrCode.Mapper<QrCodeUi> {
    override fun map(title: String, content: String, path : String): QrCodeUi =
        QrCodeUi(title = title, content = content, path = path)

}