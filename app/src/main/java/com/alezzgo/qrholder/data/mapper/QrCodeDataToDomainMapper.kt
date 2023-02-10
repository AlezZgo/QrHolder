package com.alezzgo.qrholder.data.mapper

import com.alezzgo.qrholder.data.QrCodeData
import com.alezzgo.qrholder.domain.model.QrCode
import javax.inject.Inject

class QrCodeDataToDomainMapper @Inject constructor() : QrCodeData.Mapper<QrCode> {
    override fun map(title: String, content: String, path: String) = QrCode(title, content, path)
}