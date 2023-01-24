package com.example.qrholder.core

interface Delete<T> {
    suspend fun delete(model : T)
}
