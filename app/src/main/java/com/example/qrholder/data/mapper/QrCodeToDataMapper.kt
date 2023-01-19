package com.example.qrholder.data.mapper

import com.example.qrholder.data.QrCodeData
import com.example.qrholder.data.cache.db.QrCodeCache
import com.example.qrholder.domain.model.QrCode
import javax.inject.Inject

class QrCodeToDataMapper @Inject constructor() : QrCode.Mapper<QrCodeData> {
    override fun map(title: String, content: String, path: String) =
        QrCodeData(title, content, path)
}