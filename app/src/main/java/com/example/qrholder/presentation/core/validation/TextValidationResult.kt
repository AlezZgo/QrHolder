package com.example.qrholder.presentation.core.validation


interface ValidationResult<T>

sealed class TextValidationResult : ValidationResult<String> {

    data class Success(
        private val text: String
    ) : TextValidationResult()

    data class Error(
        private val message: String
    ) : TextValidationResult()

}
