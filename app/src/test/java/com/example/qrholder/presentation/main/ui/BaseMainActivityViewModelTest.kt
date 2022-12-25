package com.example.qrholder.presentation.main.ui

import com.example.qrholder.core.TestDispatchersList
import com.example.qrholder.presentation.main.MainActivityViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.BeforeEach

abstract class BaseMainActivityViewModelTest {


    protected lateinit var viewModel: MainActivityViewModel
    protected lateinit var dispatchersList: TestDispatchersList
    protected lateinit var fabState: TestFabStateCommunication

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")


    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)

        fabState = TestFabStateCommunication()
        dispatchersList = TestDispatchersList()

        viewModel = MainActivityViewModel(
            fabState = fabState,
            dispatchers = dispatchersList
        )
    }

}