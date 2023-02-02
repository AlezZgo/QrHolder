package com.example.qrholder.presentation.core.validation

import com.example.qrholder.R
import com.example.qrholder.core.ManageResources
import javax.inject.Inject

class LengthCondition (
    private val min: Int = 0,
    private val max: Int = Int.MAX_VALUE,
    private val manageResources: ManageResources
) : TextCondition {

    override fun validate(model: String): TextValidationResult {

        if (min < 0) throw IllegalArgumentException(manageResources.string(R.string.field_MIN_cannot_be_less_then_0))
        if (max <= 0) throw IllegalArgumentException(manageResources.string(R.string.field_MAX_cannot_be_less_or_equal_0))

        val trimmedText = model.trim()

        return when {
            min != 0 && trimmedText.isBlank() -> TextValidationResult.Error(
                manageResources.string(
                    R.string.input_edit_text_error_this_field_cannot_be_empty
                )
            )
            trimmedText.length < min -> TextValidationResult.Error(
                manageResources.string(
                    R.string.input_edit_text_error_this_field_must_contain_at_least_d_characters,
                    min.toString()
                )
            )
            trimmedText.length > max -> TextValidationResult.Error(
                manageResources.string(
                    R.string.input_edit_text_error_this_field_must_contain_no_more_then_d_characters,
                    max.toString()
                )
            )
            else -> TextValidationResult.Success(trimmedText)
        }

    }
}