package com.alezzgo.qrholder.presentation.main.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.alezzgo.qrholder.presentation.main.QrCodeScannedCommunication

class TestScanResultCommunication : QrCodeScannedCommunication {

    var result = ""

    override fun observe(owner: LifecycleOwner, observer: Observer<String>) = Unit

    override fun map(source: String) {
        result = source
    }

}
