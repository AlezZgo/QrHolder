package com.alezzgo.qrholder.presentation.buildQrCode

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.alezzgo.qrholder.presentation.core.validation.TextValidationResult

class TestValidateContentCommunication : ValidateContentCommunication {

    var result = mutableListOf<TextValidationResult>()

    override fun observe(owner: LifecycleOwner, observer: Observer<TextValidationResult>) = Unit

    override fun map(source: TextValidationResult) {
        result.add(source)
    }

}