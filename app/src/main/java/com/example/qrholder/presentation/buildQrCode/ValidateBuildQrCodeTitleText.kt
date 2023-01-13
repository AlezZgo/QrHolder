package com.example.qrholder.presentation.buildQrCode

import com.example.qrholder.presentation.core.validation.LengthCondition
import com.example.qrholder.presentation.core.validation.TextValidationResult
import com.example.qrholder.presentation.core.validation.ValidateText

class ValidateBuildQrCodeTitleText : ValidateText<TextValidationResult>(
    listOf(LengthCondition(5, 50))
)