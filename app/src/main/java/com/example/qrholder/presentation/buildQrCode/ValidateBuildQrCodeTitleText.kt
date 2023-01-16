package com.example.qrholder.presentation.buildQrCode

import com.example.qrholder.presentation.core.validation.LengthCondition
import com.example.qrholder.presentation.core.validation.TextCondition
import com.example.qrholder.presentation.core.validation.TextValidationResult
import com.example.qrholder.presentation.core.validation.ValidateText
import javax.inject.Inject

class ValidateBuildQrCodeTitleText @Inject constructor() : ValidateText(
    listOf(LengthCondition(5, 50))
)