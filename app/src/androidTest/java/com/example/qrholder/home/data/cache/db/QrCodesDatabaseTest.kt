package com.example.qrholder.home.data.cache.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.qrholder.home.domain.QrCode
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
internal class QrCodesDatabaseTest {

    private lateinit var db: QrCodesDatabase
    private lateinit var dao: QrCodesDao

    @Before
    fun createDatabase() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context,QrCodesDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.qrCodesDao()
    }

    @After
    @Throws(IOException::class)
    fun clearDatabase(){
        db.close()
    }

    @Test
    fun insertFetch(){
        var actual = dao.allQrCodes()
        assertEquals(emptyList<QrCodeCache>(),actual)

        val qrCode = QrCodeCache("Cat", "www.cat.com","content.cat.id1")
        dao.insert(qrCode)
        actual = dao.allQrCodes()

        assertEquals(listOf(qrCode),actual)

        val qrCodeWithTheSameTitle = QrCodeCache("Cat", "www.cat.com2","content.cat.id2")
        dao.insert(qrCodeWithTheSameTitle)
        actual = dao.allQrCodes()

        assertEquals(listOf(qrCodeWithTheSameTitle),actual)

        val qrCodeWithAnotherTitle = QrCodeCache("Dog", "www.dog.com","content.dog.id1")
        dao.insert(qrCodeWithAnotherTitle)
        actual = dao.allQrCodes()

        assertEquals(listOf(qrCodeWithTheSameTitle,qrCodeWithAnotherTitle),actual)
    }
}