package com.example.qrholder.data

import com.example.qrholder.data.cache.QrCodesCacheDataSource
import com.example.qrholder.data.cache.db.QrCodeCache
import com.example.qrholder.domain.model.QrCode
import com.example.qrholder.domain.model.QrCodes
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class QrCodesRepositoryTest : AbstractQrCodesRepositoryTest() {

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
                    content = "www.something.test",
                    path = "android.test.id1"
                ),
                QrCodeData(
                    title = "Test title 2",
                    content = "www.something.test",
                    path = "android.test.id2"
                ),
                QrCodeData(
                    title = "Test title 3",
                    content = "www.something.test",
                    path = "android.test.id3"
                )
            )
        )

        val expected = QrCodes.Success(
            listOf(
                QrCode(
                    title = "Test title 1",
                    content = "www.something.test",
                    path = "android.test.id1"
                ),
                QrCode(
                    title = "Test title 2",
                    content = "www.something.test",
                    path = "android.test.id2"
                ),
                QrCode(
                    title = "Test title 3",
                    content = "www.something.test",
                    path = "android.test.id3"
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

        private var list = mutableListOf<QrCodeData>()
        private var exception = Exception("something went wrong")
        private var expectedError = false
        var systemSettingsNeverShow = false
        val expectedQrCode = QrCodeCache("","","",12983471924L)

        fun changeExpectedResult(list: List<QrCodeData>) {
            expectedError = false
            this.list = list.toMutableList()
        }

        fun changeExpectedError(exception: Exception) {
            expectedError = true
            this.exception = exception
        }

        override suspend fun allQrCodes() = if (expectedError) throw exception else list

        override suspend fun save(qrCode: QrCodeData) {
            list.add(qrCode)
        }

        override suspend fun delete(qrCodeTitle: String) = Unit

        override fun fetchSystemSettingsNeverShow(): Boolean {
            return systemSettingsNeverShow
        }

        override fun saveSystemSettingsNeverShow(neverShow: Boolean) {
            systemSettingsNeverShow = neverShow
        }

        override suspend fun fetchQrCode(title: String): QrCodeCache {
            return expectedQrCode
        }
    }
}