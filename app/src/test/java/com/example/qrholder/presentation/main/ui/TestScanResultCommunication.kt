package com.example.qrholder.presentation.main.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.qrholder.presentation.main.QrCodeScannedCommunication
import com.example.qrholder.presentation.scanFromGallery.ScanQrCodeResult
import com.example.qrholder.presentation.scanFromGallery.ScanResultCommunication

class TestScanResultCommunication : QrCodeScannedCommunication {

    var result = ""

    override fun observe(owner: LifecycleOwner, observer: Observer<String>) = Unit

    override fun map(source: String) {
        result = source
    }

}
