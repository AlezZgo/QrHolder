package com.example.qrholder.presentation.buildQrCode

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.qrholder.presentation.home.model.QrCodeUi

class TestQrCodeBuiltCommunication : QrCodeBuiltCommunication {

    var buildResult = mutableListOf<QrCodeBuildResult>()

    override fun observe(owner: LifecycleOwner, observer: Observer<QrCodeBuildResult>) = Unit

    override fun map(source: QrCodeBuildResult) {
        buildResult.add(source)
    }


}