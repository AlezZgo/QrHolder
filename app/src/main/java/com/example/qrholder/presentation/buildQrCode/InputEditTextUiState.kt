package com.example.qrholder.presentation.buildQrCode

import com.google.android.material.textfield.TextInputEditText

sealed class InputEditTextUiState {

    abstract fun show(textInputEditText: TextInputEditText)

    object Success : InputEditTextUiState(){
        override fun show(textInputEditText: TextInputEditText) {
            textInputEditText.error = null
        }
    }

    data class Error(
        private val error: String
    ) : InputEditTextUiState(){
        override fun show(textInputEditText: TextInputEditText) {
            textInputEditText.error = this.error
        }
    }
}