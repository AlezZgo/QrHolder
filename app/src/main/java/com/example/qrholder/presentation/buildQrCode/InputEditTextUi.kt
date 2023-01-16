package com.example.qrholder.presentation.buildQrCode

import com.google.android.material.textfield.TextInputEditText

sealed class InputEditTextUi {

    abstract fun show(textInputEditText: TextInputEditText)

    object NoError : InputEditTextUi(){
        override fun show(textInputEditText: TextInputEditText) {
            textInputEditText.error = null
        }
    }

    data class Error(
        private val error: String
    ) : InputEditTextUi(){
        override fun show(textInputEditText: TextInputEditText) {
            textInputEditText.error = this.error
        }
    }
}