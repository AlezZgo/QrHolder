package com.example.qrholder.presentation.home.domain

import com.example.qrholder.domain.HomeInteractor
import com.example.qrholder.domain.QrCode
import com.example.qrholder.domain.QrCodes
import com.example.qrholder.data.QrCodesRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HomeInteractorTest : AbstractHomeInteractorTest() {

    @Test
    fun `fetch success empty list`() = runBlocking{
        val emptyQrCodes = QrCodes.Success(emptyList())
        repository.changeExpectedResult(emptyQrCodes)
        assertEquals(emptyQrCodes,repository.allQrCodes())
        assertEquals(1,repository.allNumbersCalledCount)
    }

    @Test
    fun `fetch success list`() = runBlocking{
        val qrCodes = QrCodes.Success(
            qrCodes = listOf(
                QrCode(
                    title = "Test title 1",
                    content = "www.something.test",
                    path = "content.cat.id1"
                ),
                QrCode(
                    title = "Test title 2",
                    content = "www.something.test",
                    path = "content.cat.id2"
                ),
                QrCode(
                    title = "Test title 3",
                    content = "www.something.test",
                    path = "content.cat.id3"
                )
            )
        )

        repository.changeExpectedResult(qrCodes)
        assertEquals(qrCodes,repository.allQrCodes())
        assertEquals(1,repository.allNumbersCalledCount)
    }

    @Test
    fun `handle error`() = runBlocking{
        val error = QrCodes.Failure("something went wrong")
        repository.changeExpectedResult(error)
        assertEquals(error,repository.allQrCodes())
        assertEquals(1,repository.allNumbersCalledCount)
    }

}