package com.example.qrholder.home.data

import com.example.qrholder.core.ManageResources
import com.example.qrholder.home.domain.QrCode
import com.example.qrholder.home.domain.QrCodes
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class QrCodesRepositoryTest {

    private lateinit var repository: QrCodesRepository
    private lateinit var mapper: QrCodeDataToDomainMapper
    private lateinit var cacheDataSource: TestQrCodesCacheDataSource
    private lateinit var manageResources: TestManageResources

    @BeforeEach
    fun setUp() {
        cacheDataSource = TestQrCodesCacheDataSource()
        manageResources = TestManageResources()
        mapper = QrCodeDataToDomainMapper()
        repository = QrCodesRepository.Base(cacheDataSource, mapper,manageResources)
    }

    @Test
    fun `fetch empty list`() = runBlocking {
        cacheDataSource.changeExpectedResult(emptyList())
        assertEquals(QrCodes.Success(emptyList()), repository.allQrCodes())
    }

    @Test
    fun `fetch list`() = runBlocking {
        cacheDataSource.changeExpectedResult(
            listOf(
                QrCodeData(
                    title = "Test title 1",
                    content = "www.something.test"
                ),
                QrCodeData(
                    title = "Test title 2",
                    content = "www.something.test"
                ),
                QrCodeData(
                    title = "Test title 3",
                    content = "www.something.test"
                )
            )
        )

        val expected = QrCodes.Success(
            listOf(
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

        assertEquals(expected, repository.allQrCodes())
    }

    @Test
    fun `try to fetch list, but error`() = runBlocking {
        cacheDataSource.changeExpectedError(Exception("Something went wrong test"))
        val expected = QrCodes.Failure("Something went wrong test")
        assertEquals(expected, repository.allQrCodes())
    }

    class TestQrCodesCacheDataSource : QrCodesCacheDataSource {

        private var list = emptyList<QrCodeData>()
        private var exception = Exception("something went wrong")
        private var expectedError = false

        fun changeExpectedResult(list: List<QrCodeData>) {
            expectedError = false
            this.list = list
        }

        fun changeExpectedError(exception: Exception) {
            expectedError = true
            this.exception = exception
        }

        override suspend fun allQrCodes() = if (expectedError) throw exception else list
    }

    class TestManageResources : ManageResources {
        private var value = ""

        fun changeExpected(string: String) {
            value = string
        }

        override fun string(id: Int): String = value
    }
}