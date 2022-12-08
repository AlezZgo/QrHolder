package com.example.qrholder.home.domain

import com.example.qrholder.home.data.QrCodesRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HomeInteractorTest {

    private lateinit var interactor: HomeInteractor
    private lateinit var repository: TestQrCodesRepository

    @BeforeEach
    fun setUp(){
        repository = TestQrCodesRepository()
        interactor = HomeInteractor.Base(repository)
    }

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
                    content = "www.something.test"
                ),
                QrCode(
                    title = "Test title 2",
                    content = "www.something.test"
                ),
                QrCode(
                    title = "Test title 3",
                    content = "www.something.test"
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

    private class TestQrCodesRepository : QrCodesRepository {

        private var expectedQrCodesResult : QrCodes = QrCodes.Success(emptyList())
        var allNumbersCalledCount = 0

        fun changeExpectedResult(qrCodes : QrCodes){
            expectedQrCodesResult = qrCodes
        }

        override suspend fun allQrCodes() : QrCodes {
            allNumbersCalledCount++
            return expectedQrCodesResult
        }

    }

}