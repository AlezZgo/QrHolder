package com.alezzgo.qrholder.data.mapper

import com.alezzgo.qrholder.data.QrCodeData
import com.alezzgo.qrholder.domain.model.QrCode
import javax.inject.Inject

class QrCodeToDataMapper @Inject constructor() : QrCode.Mapper<QrCodeData> {
    override fun map(title: String, content: String, path: String) =
        QrCodeData(title, content, path)
}