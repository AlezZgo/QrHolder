package com.example.qrholder.home.data

import com.example.qrholder.home.domain.QrCode
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class QrCodeDataToDomainMapperTest{


    @Test
    fun `convert Data to domain model`(){

        val mapper = QrCodeDataToDomainMapper()

        val dataModel = QrCodeData("Title", "Content")

        val actual = dataModel.map(mapper)

        assertEquals(QrCode("Title", "Content"),actual)


    }
}