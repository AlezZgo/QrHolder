package com.alezzgo.qrholder.presentation.core.validation

import com.google.android.material.textfield.TextInputEditText

interface ValidationResult<T>

sealed class TextValidationResult : ValidationResult<String> {

    abstract fun show(textInputEditText: TextInputEditText)

    data class Success(
        private val text: String
    ) : TextValidationResult() {

        override fun show(textInputEditText: TextInputEditText) {
            textInputEditText.error = null
        }
    }

    data class Error(
        private val message: String
    ) : TextValidationResult() {

        override fun show(textInputEditText: TextInputEditText) {
            textInputEditText.error = message
        }
    }

}
