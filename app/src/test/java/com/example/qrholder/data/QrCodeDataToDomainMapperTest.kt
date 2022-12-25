package com.example.qrholder.data

import com.example.qrholder.domain.QrCode
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class QrCodeDataToDomainMapperTest {

    @Test
    fun `convert Data to domain model`() {

        val mapper = QrCodeDataToDomainMapper()

        val dataModel = QrCodeData("Title", "Content", path = "content.cat.id1")

        val actual = dataModel.map(mapper)

        assertEquals(QrCode("Title", "Content", path = "content.cat.id1"), actual)

    }
}