package com.example.qrholder.presentation.buildQrCode

import com.example.qrholder.presentation.core.validation.TextValidationResult
import com.example.qrholder.presentation.core.validation.Validate
import com.example.qrholder.presentation.core.validation.ValidateText
import com.example.qrholder.presentation.core.validation.ValidationResult

class TestValidateText : Validate<String, TextValidationResult> {

    private var validationResult : TextValidationResult = TextValidationResult.Success("some success validation text")

    fun changeValidationResult(result : TextValidationResult){
        validationResult = result
    }

    override fun validate(model: String): TextValidationResult {
        return validationResult
    }

}
