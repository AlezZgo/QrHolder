package com.alezzgo.qrholder.presentation.core.validation

interface Condition<T, R> {
    fun validate(model: T): R
}