package com.example.qrholder.data

import com.example.qrholder.domain.QrCode
import javax.inject.Inject

class QrCodeDataToDomainMapper @Inject constructor() : QrCodeData.Mapper<QrCode> {
    override fun map(title: String, content: String, path: String) = QrCode(title, content, path)
}