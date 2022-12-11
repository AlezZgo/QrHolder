package com.example.qrholder.home.data

import com.example.qrholder.home.domain.QrCode

class QrCodeDataToDomainMapper : QrCodeData.Mapper<QrCode> {
    override fun map(title: String, content: String, path : String) = QrCode(title,content,path)
}