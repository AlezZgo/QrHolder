package com.example.qrholder.presentation.core.validation

interface Validate<T,R> {

    fun validate(
        model : T
    ) : R

}
