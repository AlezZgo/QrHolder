package com.example.qrholder.presentation.buildQrCode

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.qrholder.domain.model.QrCodeBuildResult
import com.example.qrholder.presentation.core.viewmodel.Communication
import javax.inject.Inject

interface ShowQrCodeBuildResultState{
    fun showBuildResultState(qrCode: QrCodeBuildResult)
}

interface ObserveQrCodeBuildResult{
    fun observeBuildResultState(owner: LifecycleOwner, observer: Observer<QrCodeBuildResult>)
}

interface BuildQrCodeResultCommunication : Communication.Mutable<QrCodeBuildResult>{
    class Base @Inject constructor() : Communication.Post<QrCodeBuildResult>(),BuildQrCodeResultCommunication
}

