package com.example.qrholder.home.ui.mapper

import com.example.qrholder.home.domain.QrCode
import com.example.qrholder.home.ui.QrCodeUi
import javax.inject.Inject

class QrCodeToUiMapper @Inject constructor() : QrCode.Mapper<QrCodeUi> {
    override fun map(title: String, content: String, path : String): QrCodeUi =
        QrCodeUi(title = title, content = content, path = path)

}