package com.example.qrholder.data.cache

import com.example.qrholder.data.QrCodeData
import com.example.qrholder.data.cache.QrCodesCacheDataSource
import com.example.qrholder.data.cache.db.QrCodeCache
import com.example.qrholder.data.cache.db.QrCodesDao
import com.example.qrholder.data.mapper.QrCodeDataToCacheMapper
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class QrCodesCacheDataSourceTest {

    private lateinit var cacheDataSource: QrCodesCacheDataSource
    private lateinit var dao: TestQrCodesDao
    private lateinit var mapper : QrCodeDataToCacheMapper

    @BeforeEach
    fun setUp() {
        dao = TestQrCodesDao()
        mapper = QrCodeDataToCacheMapper()
        cacheDataSource = QrCodesCacheDataSource.Base(dao,mapper)
    }

    @Test
    fun `fetch all qr codes`() = runBlocking {
        dao.changeExpectedResult(
            listOf(
                QrCodeCache(
                    title = "test title 1",
                    content = "test1@gmail.com",
                    path = "android.images.12022022190056.png",
                    1
                ),
                QrCodeCache(
                    title = "test title 2",
                    content = "test2@gmail.com",
                    path = "android.images.12022022190052.png",
                    2
                ),
                QrCodeCache(
                    title = "test title 3",
                    content = "test3@gmail.com",
                    path = "android.images.12022022190055.png",
                    3
                ),
            )
        )

        val actual = cacheDataSource.allQrCodes()
        val expected = listOf(
            QrCodeData(
                title = "test title 1",
                content = "test1@gmail.com",
                path = "android.images.12022022190056.png"
            ),
            QrCodeData(
                title = "test title 2",
                content = "test2@gmail.com",
                path = "android.images.12022022190052.png"
            ),
            QrCodeData(
                title = "test title 3",
                content = "test3@gmail.com",
                path = "android.images.12022022190055.png"
            ),
        )
        assertEquals(expected, actual)

    }

    @Test
    fun `insert qr code`() = runBlocking {

        dao.insert(
            QrCodeCache(
                title = "test title 1",
                content = "test1@gmail.com",
                path = "android.images.12022022190056.png",
                1
            )
        )

        val actual = cacheDataSource.allQrCodes()
        val expected = listOf(
            QrCodeData(
                title = "test title 1",
                content = "test1@gmail.com",
                path = "android.images.12022022190056.png"
            )
        )

        assertEquals(expected, actual)

    }

    private class TestQrCodesDao : QrCodesDao {

        private var qrCodes = mutableListOf<QrCodeCache>()

        fun changeExpectedResult(qrCodes: List<QrCodeCache>) {
            this.qrCodes.clear()
            this.qrCodes = qrCodes.toMutableList()
        }

        override fun allQrCodes(): List<QrCodeCache> = qrCodes

        override fun insert(qrCode: QrCodeCache) {
            qrCodes.add(qrCode)
        }

        override fun clearAll() {
            qrCodes.clear()
        }

    }
}

