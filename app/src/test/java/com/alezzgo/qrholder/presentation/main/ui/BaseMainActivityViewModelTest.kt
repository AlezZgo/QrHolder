package com.alezzgo.qrholder.presentation.main.ui

import com.alezzgo.qrholder.core.TestDispatchersList
import com.alezzgo.qrholder.presentation.main.MainActivityViewModel
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
    protected lateinit var scanResult: TestScanResultCommunication

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")


    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)

        fabState = TestFabStateCommunication()
        dispatchersList = TestDispatchersList()
        scanResult = TestScanResultCommunication()

        viewModel = MainActivityViewModel(
            fabState = fabState,
            dispatchers = dispatchersList,
            qrCodeScannedCommunication = scanResult
        )
    }

}