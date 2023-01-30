package com.example.qrholder.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import ru.mintrocket.lib.mintpermissions.ext.initMintPermissions

@HiltAndroidApp
class QrHolderApp : Application(){
    override fun onCreate() {
        super.onCreate()
        initMintPermissions()
    }
}