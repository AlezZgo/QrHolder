package com.alezzgo.qrholder.presentation.core.viewmodel

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatchersList {

    fun io(): CoroutineDispatcher

    fun ui(): CoroutineDispatcher

    class Base : DispatchersList {
        override fun io() = Dispatchers.IO
        override fun ui() = Dispatchers.Main
    }

}
