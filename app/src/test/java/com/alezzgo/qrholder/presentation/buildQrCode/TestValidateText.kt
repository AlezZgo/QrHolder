package com.alezzgo.qrholder.presentation.buildQrCode

import com.alezzgo.qrholder.presentation.core.validation.TextValidationResult
import com.alezzgo.qrholder.presentation.core.validation.Validate

class TestValidateText : Validate<String, TextValidationResult> {

    private var validationResult: TextValidationResult =
        TextValidationResult.Success("some success validation text")

    fun changeValidationResult(result: TextValidationResult) {
        validationResult = result
    }

    override fun validate(model: String): TextValidationResult {
        return validationResult
    }

}
