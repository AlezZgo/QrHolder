package com.example.qrholder.home.data.cache


interface QrCodesDao {

    fun allQrCodes() : List<QrCodeCache>

}