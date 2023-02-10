package com.alezzgo.qrholder.presentation.main.ui

import com.alezzgo.qrholder.presentation.main.MainFabUiState
import kotlinx.coroutines.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class MainActivityViewModelTest : BaseMainActivityViewModelTest() {

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