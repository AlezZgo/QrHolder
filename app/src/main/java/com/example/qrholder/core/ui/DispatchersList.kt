package com.example.qrholder.core.ui

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

interface DispatchersList {

    fun io() : CoroutineDispatcher

    fun ui() : CoroutineDispatcher

    class Base : DispatchersList {
        override fun io() = Dispatchers.IO
        override fun ui() = Dispatchers.Main
    }

}
