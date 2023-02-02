package com.example.qrholder.presentation.buildQrCode

import com.example.qrholder.core.ManageResources
import com.example.qrholder.presentation.core.validation.LengthCondition
import com.example.qrholder.presentation.core.validation.TextCondition
import com.example.qrholder.presentation.core.validation.TextValidationResult
import com.example.qrholder.presentation.core.validation.ValidateText
import javax.inject.Inject

class ValidateBuildQrCodeTitleText @Inject constructor(
    manageResources: ManageResources
) : ValidateText(
    listOf(LengthCondition(3, 50,manageResources))
)