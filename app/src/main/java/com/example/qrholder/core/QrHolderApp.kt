package com.example.qrholder.core

import android.app.Application
import com.example.qrholder.di.MockClass
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class QrHolderApp : Application() {

    @Inject lateinit var mock : MockClass

    override fun onCreate() {
        super.onCreate()
        println(mock.test())
    }
}