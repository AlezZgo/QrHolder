package com.example.qrholder.home.ui

import com.example.qrholder.domain.QrCode
import com.example.qrholder.presentation.home.mapper.QrCodeToUiMapper
import com.example.qrholder.presentation.home.mapper.QrCodesMapper
import com.example.qrholder.presentation.home.QrCodeUi
import com.example.qrholder.presentation.home.QrCodeUiCompleteList
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
            QrCode("Cat", "www.cat.com","content.cat.id1"),
            QrCode("Dog", "www.dog.com","content.cat.id2"),
            QrCode("Duck", "www.duck.com","content.cat.id3"),
            QrCode("Whale", "www.whale.com","content.cat.id4"),
        )

        qrCodesMapper.map(testList, "")

        assertEquals(1, communications.qrCodesCompleteCalledList.size)
        assertEquals(
            QrCodeUiCompleteList.Success(
                listOf(
                    QrCodeUi("Cat", "www.cat.com","content.cat.id1"),
                    QrCodeUi("Dog", "www.dog.com","content.cat.id2"),
                    QrCodeUi("Duck", "www.duck.com","content.cat.id3"),
                    QrCodeUi("Whale", "www.whale.com","content.cat.id4"),
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