package com.example.qrholder.main.ui

import com.example.qrholder.home.ui.BaseHomeViewModelTest
import com.example.qrholder.presentation.main.MainActivityViewModel
import com.example.qrholder.presentation.main.MainFabUiState
import kotlinx.coroutines.*
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class MainActivityViewModelTest : BaseMainViewModelTest(){

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var dispatchersList: BaseHomeViewModelTest.TestDispatchersList
    private lateinit var fabState: TestFabStateCommunication

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")


    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)

        fabState = TestFabStateCommunication()
        dispatchersList = BaseHomeViewModelTest.TestDispatchersList()

        viewModel = MainActivityViewModel(
            fabState = fabState,
            dispatchers = dispatchersList
        )
    }

    @Test
    fun `first run app, re-init,changeFabState, re-init, changeFabState`() = runBlocking {

        viewModel.init(true)

        assertEquals(1, fabState.fabStateCalledList.size)
        assertEquals(MainFabUiState.Closed, fabState.fabStateCalledList[0])

        viewModel.init(false)

        assertEquals(1, fabState.fabStateCalledList.size)
        assertEquals(MainFabUiState.Closed, fabState.fabStateCalledList[0])

        viewModel.changeFabState(MainFabUiState.Opened)

        assertEquals(2, fabState.fabStateCalledList.size)
        assertEquals(MainFabUiState.Opened, fabState.fabStateCalledList[1])

        viewModel.init(false)

        assertEquals(2, fabState.fabStateCalledList.size)
        assertEquals(MainFabUiState.Opened, fabState.fabStateCalledList[1])

        viewModel.changeFabState(MainFabUiState.Closed)

        assertEquals(3, fabState.fabStateCalledList.size)
        assertEquals(MainFabUiState.Closed, fabState.fabStateCalledList[2])

    }



}