package com.example.qrholder.presentation.buildQrCode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class BuildQrCodeViewModelTest : AbstractBuildQrCodeViewModelTest() {

    @Test
    fun `first run app`() {

        viewModel.init(true)
        assertEquals(1, communications.titleCommunication.inputEditTextUiStateCalledList.size)
        assertEquals(
            InputEditTextUiState.NoError,
            communications.titleCommunication.inputEditTextUiStateCalledList[0]
        )

    }

}