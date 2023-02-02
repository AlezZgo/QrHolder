package com.example.qrholder.core

interface Mapper<S, R> {

    fun map(source: S): R

    interface Unit<S> : Mapper<S, kotlin.Unit>
}