package com.alezzgo.qrholder.presentation.buildQrCode

import com.alezzgo.qrholder.core.ManageResources
import com.alezzgo.qrholder.presentation.core.validation.LengthCondition
import com.alezzgo.qrholder.presentation.core.validation.ValidateText
import javax.inject.Inject

class ValidateBuildQrCodeContentText @Inject constructor(
    manageResources: ManageResources
) : ValidateText(
    listOf(LengthCondition(3, 300, manageResources))
)