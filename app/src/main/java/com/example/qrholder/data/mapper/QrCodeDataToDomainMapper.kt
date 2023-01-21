package com.example.qrholder.data.mapper

import com.example.qrholder.data.QrCodeData
import com.example.qrholder.domain.model.QrCode
import javax.inject.Inject

class QrCodeDataToDomainMapper @Inject constructor() : QrCodeData.Mapper<QrCode> {
    override fun map(title: String, content: String, path: String) = QrCode(title, content, path)
}