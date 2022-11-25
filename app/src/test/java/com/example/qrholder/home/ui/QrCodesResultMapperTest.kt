package com.example.qrholder.home.ui

import com.example.qrholder.home.BaseTest
import com.example.qrholder.home.domain.QrCode
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

internal class QrCodesResultMapperTest : BaseTest(){

    @Test
    fun `handle empty list and empty filter`() {

        val qrCodesResultMapper = QrCodesResultMapper(communications,QrCodeToUiMapper())

        communications.filter("")

        qrCodesResultMapper.map(emptyList(),"")
        TODO()
    }

    @Test
    fun `handle simple list and filter`() {

        val qrCodesResultMapper = QrCodesResultMapper(communications,QrCodeToUiMapper())

        val testList = listOf(
            QrCode("Cat","www.cat.com"),
            QrCode("Dog","www.dog.com"),
            QrCode("Duck","www.duck.com"),
            QrCode("Whale","www.whale.com"),
        )

        communications.filter("d")

        qrCodesResultMapper.map(testList,"")

        TODO()
    }

}