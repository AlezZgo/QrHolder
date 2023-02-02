package com.example.qrholder.presentation.home.domain

import com.example.qrholder.domain.model.QrCode
import com.example.qrholder.domain.model.QrCodes
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HomeInteractorTest : AbstractHomeInteractorTest() {

    @Test
    fun `fetch success empty list`() = runBlocking{
        val emptyQrCodes = QrCodes.Success()
        repository.changeExpectedResult(error = "",list = emptyList())
        assertEquals(emptyQrCodes,repository.allQrCodes())
        assertEquals(1,repository.allQrCodesCalledCount)
    }

    @Test
    fun `fetch success list`() = runBlocking{
        val qrCodes = listOf(
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


        repository.changeExpectedResult(error = "", list = qrCodes)
        assertEquals(QrCodes.Success(qrCodes),repository.allQrCodes())
        assertEquals(1,repository.allQrCodesCalledCount)
    }

    @Test
    fun `handle error`() = runBlocking{
        val errorMessage = "something went wrong"
        repository.changeExpectedResult(error = errorMessage,list = emptyList())

        assertEquals(QrCodes.Failure(errorMessage),repository.allQrCodes())
        assertEquals(1,repository.allQrCodesCalledCount)
    }

}