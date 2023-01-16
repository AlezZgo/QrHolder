package com.example.qrholder.presentation.buildQrCode

import com.example.qrholder.presentation.core.SinglePost
import com.example.qrholder.presentation.core.viewmodel.Communication
import com.example.qrholder.presentation.home.model.QrCodeUi
import javax.inject.Inject

interface QrCodeBuiltCommunication : Communication.Mutable<QrCodeBuildResult> {
    class Base @Inject constructor() : SinglePost<QrCodeBuildResult>(), QrCodeBuiltCommunication
}

