package com.example.qrholder.home.data.cache.db

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.qrholder.data.cache.db.QrCodeCache
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class QrCodesDatabaseTest : DatabaseForTest() {

    @Test
    fun insertFetch(){
        var actual = dao.selectAll()
        assertEquals(emptyList<QrCodeCache>(),actual)

        val qrCode = QrCodeCache("Cat", "www.cat.com","content.cat.id1",1)
        dao.insert(qrCode)
        actual = dao.selectAll()

        assertEquals(listOf(qrCode),actual)

        val qrCodeWithTheSameTitle = QrCodeCache("Cat", "www.cat.com2","content.cat.id2",2)
        dao.insert(qrCodeWithTheSameTitle)
        actual = dao.selectAll()

        assertEquals(listOf(qrCodeWithTheSameTitle),actual)

        val qrCodeWithAnotherTitle = QrCodeCache("Dog", "www.dog.com","content.dog.id1",3)
        dao.insert(qrCodeWithAnotherTitle)
        actual = dao.selectAll()

        assertEquals(listOf(qrCodeWithAnotherTitle,qrCodeWithTheSameTitle),actual)    }
}