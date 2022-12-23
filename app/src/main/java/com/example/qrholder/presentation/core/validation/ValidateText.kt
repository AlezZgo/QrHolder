package com.example.qrholder.presentation.core.validation

abstract class ValidateText(
    private val conditions: List<TextCondition>
) : Validate<String, TextValidationResult> {

    override fun validate(model: String): TextValidationResult {
        var listValidationResult: TextValidationResult = TextValidationResult.Success(model)
        conditions.forEach { condition ->
            listValidationResult = condition.validate(model)
            if (listValidationResult is TextValidationResult.Success)
                return@forEach
            else
                return listValidationResult
        }
        return listValidationResult
    }
}
