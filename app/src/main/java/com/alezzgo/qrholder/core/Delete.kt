package com.alezzgo.qrholder.core

interface Delete<T> {
    suspend fun delete(model: T)
}
