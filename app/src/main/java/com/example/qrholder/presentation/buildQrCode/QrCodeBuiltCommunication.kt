package com.example.qrholder.presentation.buildQrCode

import com.example.qrholder.presentation.core.SinglePost
import com.example.qrholder.presentation.core.validation.TextValidationResult
import com.example.qrholder.presentation.core.viewmodel.Communication
import javax.inject.Inject

interface QrCodeBuiltCommunication : Communication.Mutable<QrCodeBuildResult> {
    class Base @Inject constructor() : Communication.Post<QrCodeBuildResult>(), QrCodeBuiltCommunication
}

interface ValidateTitleCommunication : Communication.Mutable<TextValidationResult> {
    class Base @Inject constructor() : Communication.Post<TextValidationResult>(), ValidateTitleCommunication
}

interface ValidateContentCommunication : Communication.Mutable<TextValidationResult> {
    class Base @Inject constructor() : SinglePost<TextValidationResult>(), ValidateContentCommunication
}

