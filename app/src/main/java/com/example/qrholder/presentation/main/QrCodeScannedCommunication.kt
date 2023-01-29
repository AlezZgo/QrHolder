package com.example.qrholder.presentation.main

import com.example.qrholder.presentation.buildQrCode.QrCodeBuildResult
import com.example.qrholder.presentation.core.SinglePost
import com.example.qrholder.presentation.core.viewmodel.Communication
import javax.inject.Inject

interface QrCodeScannedCommunication : Communication.Mutable<String> {
    class Base @Inject constructor() : SinglePost<String>(), QrCodeScannedCommunication
}