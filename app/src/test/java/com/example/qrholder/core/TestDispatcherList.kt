package com.example.qrholder.core

import com.example.qrholder.presentation.core.viewmodel.DispatchersList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher

class TestDispatchersList : DispatchersList {
    override fun io(): CoroutineDispatcher = TestCoroutineDispatcher()

    override fun ui(): CoroutineDispatcher = TestCoroutineDispatcher()

}