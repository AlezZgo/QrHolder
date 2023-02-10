package com.alezzgo.qrholder.presentation.core.validation

interface Validate<T, R> {

    fun validate(
        model: T
    ): R

}
