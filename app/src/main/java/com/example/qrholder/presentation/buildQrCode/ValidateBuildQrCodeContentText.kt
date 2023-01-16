package com.example.qrholder.presentation.buildQrCode

import com.example.qrholder.presentation.core.validation.LengthCondition
import com.example.qrholder.presentation.core.validation.ValidateText
import javax.inject.Inject

class ValidateBuildQrCodeContentText @Inject constructor() : ValidateText(
    listOf(LengthCondition(5, 300))
)