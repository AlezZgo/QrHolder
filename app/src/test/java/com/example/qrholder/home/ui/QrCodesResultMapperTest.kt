package com.example.qrholder.home.ui

import com.example.qrholder.home.BaseTest
import com.example.qrholder.home.domain.QrCode
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class QrCodesResultMapperTest : BaseTest() {

    private val qrCodesResultMapper = QrCodesResultMapper(communications, QrCodeToUiMapper())

    @Test
    fun `handle empty list and empty filter`() {

        qrCodesResultMapper.map(emptyList(), "")
        assertEquals(1, communications.uiStateCalledList.size)
        assertEquals(HomeUiState.Empty, communications.uiStateCalledList[0])
        assertEquals(1, communications.qrCodesCompleteCalledList.size)
        assertEquals(emptyList<QrCodeUi>(), communications.qrCodesCompleteCalledList[0])
    }

    @Test
    fun `handle simple list and empty filter`() {

        val testList = listOf(
            QrCode("Cat", "www.cat.com"),
            QrCode("Dog", "www.dog.com"),
            QrCode("Duck", "www.duck.com"),
            QrCode("Whale", "www.whale.com"),
        )

        qrCodesResultMapper.map(testList, "")

        assertEquals(1, communications.qrCodesCompleteCalledList.size)
        assertEquals(
            listOf(
                QrCodeUi("Cat", "www.cat.com"),
                QrCodeUi("Dog", "www.dog.com"),
                QrCodeUi("Duck", "www.duck.com"),
                QrCodeUi("Whale", "www.whale.com"),
            ), communications.filterCalledList[0]
        )

        assertEquals(1, communications.uiStateCalledList.size)
        assertEquals(
            HomeUiState.Success(
                listOf(
                    QrCodeUi("Cat", "www.cat.com"),
                    QrCodeUi("Dog", "www.dog.com"),
                    QrCodeUi("Duck", "www.duck.com"),
                    QrCodeUi("Whale", "www.whale.com"),
                )
            ), communications.uiStateCalledList[0]
        )
    }

    @Test
    fun `handle simple list and filter`() {

        val testList = listOf(
            QrCode("Cat", "www.cat.com"),
            QrCode("Dog", "www.dog.com"),
            QrCode("Duck", "www.duck.com"),
            QrCode("Whale", "www.whale.com"),
        )

        val filter = "d"

        communications.filter(filter)

        qrCodesResultMapper.map(testList, "")

        assertEquals(2, communications.filterCalledList.size)
        assertEquals(filter, communications.filterCalledList[1])

        assertEquals(1, communications.qrCodesCompleteCalledList.size)
        assertEquals(
            listOf(
                QrCodeUi("Cat", "www.cat.com"),
                QrCodeUi("Dog", "www.dog.com"),
                QrCodeUi("Duck", "www.duck.com"),
                QrCodeUi("Whale", "www.whale.com"),
            ), communications.filterCalledList[0]
        )

        assertEquals(1, communications.uiStateCalledList.size)
        assertEquals(
            HomeUiState.Success(
                listOf(
                    QrCodeUi("Dog", "www.dog.com"),
                    QrCodeUi("Duck", "www.duck.com")
                )
            ), communications.uiStateCalledList[0]
        )
    }

    @Test
    fun `handle error`() {

        val errorMessage = "Something went wrong"

        qrCodesResultMapper.map(emptyList(), errorMessage)

        assertEquals(1, communications.uiStateCalledList.size)
        assertEquals(HomeUiState.Error(errorMessage), communications.uiStateCalledList[0])
        assert(communications.qrCodesCompleteCalledList.isEmpty())

    }

}