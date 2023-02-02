package com.example.qrholder.home.data.cache.db.core

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.qrholder.data.cache.db.QrCodesDao
import com.example.qrholder.data.cache.db.QrCodesDatabase
import org.junit.After
import org.junit.Before
import java.io.IOException

abstract class DatabaseForTest {

    protected lateinit var db: QrCodesDatabase
    protected lateinit var dao: QrCodesDao

    @Before
    fun createDatabase() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, QrCodesDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        dao = db.qrCodesDao()

    }

    @After
    @Throws(IOException::class)
    fun clearDatabase(){
        db.close()
    }



}