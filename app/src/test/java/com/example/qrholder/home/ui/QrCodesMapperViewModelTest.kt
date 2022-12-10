package com.example.qrholder.home.ui

import com.example.qrholder.home.domain.QrCode
import com.example.qrholder.home.ui.mapper.QrCodeToUiMapper
import com.example.qrholder.home.ui.mapper.QrCodesMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class QrCodesMapperViewModelTest : BaseViewModelTest() {

    private val communications = TestHomeCommunications()
    private val qrCodesMapper = QrCodesMapper(communications, QrCodeToUiMapper())

    @Test
    fun `handle empty list`() {
        qrCodesMapper.map(emptyList(), "")
        assertEquals(1, communications.qrCodesCompleteCalledList.size)
        assertEquals(
            QrCodeUiCompleteList.Success(emptyList()),
            communications.qrCodesCompleteCalledList[0]
        )
    }

    @Test
    fun `handle simple list`() {

        val testList = listOf(
            QrCode("Cat", "www.cat.com"),
            QrCode("Dog", "www.dog.com"),
            QrCode("Duck", "www.duck.com"),
            QrCode("Whale", "www.whale.com"),
        )

        qrCodesMapper.map(testList, "")

        assertEquals(1, communications.qrCodesCompleteCalledList.size)
        assertEquals(
            QrCodeUiCompleteList.Success(
                listOf(
                    QrCodeUi("Cat", "www.cat.com"),
                    QrCodeUi("Dog", "www.dog.com"),
                    QrCodeUi("Duck", "www.duck.com"),
                    QrCodeUi("Whale", "www.whale.com"),
                )
            ), communications.qrCodesCompleteCalledList[0]
        )

    }

    @Test
    fun `handle error`() {

        val errorMessage = "Something went wrong"
        qrCodesMapper.map(emptyList(), errorMessage)
        assertEquals(1, communications.qrCodesCompleteCalledList.size)
        assertEquals(QrCodeUiCompleteList.Error(errorMessage), communications.qrCodesCompleteCalledList[0])

    }

}