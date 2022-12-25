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

    @Test
    fun `change title input text`() {

        viewModel.changeTitle("")
        manageResources.changeExpected("This field cannot be empty")

        assertEquals(1, communications.titleCommunication.inputEditTextUiStateCalledList.size)
        assertEquals(
            InputEditTextUiState.Error(manageResources.string(0)),
            communications.titleCommunication.inputEditTextUiStateCalledList[0]
        )

        viewModel.changeTitle("h")
        manageResources.changeExpected("This field must contain at least 5 characters")

        assertEquals(2, communications.titleCommunication.inputEditTextUiStateCalledList.size)
        assertEquals(
            InputEditTextUiState.Error(manageResources.string(0)),
            communications.titleCommunication.inputEditTextUiStateCalledList[1]
        )

        viewModel.changeTitle("Bower")

        assertEquals(3, communications.titleCommunication.inputEditTextUiStateCalledList.size)
        assertEquals(
            InputEditTextUiState.NoError,
            communications.titleCommunication.inputEditTextUiStateCalledList[2]
        )

        viewModel.changeTitle("Ow my god, This title contains more then fifty symbols")
        manageResources.changeExpected("This field must contain no more than 50 characters")

        assertEquals(4, communications.titleCommunication.inputEditTextUiStateCalledList.size)
        assertEquals(
            InputEditTextUiState.Error(manageResources.string(0)),
            communications.titleCommunication.inputEditTextUiStateCalledList[3]
        )
    }

    @Test
    fun `change content input text`() {

        viewModel.changeContent("")
        manageResources.changeExpected("This field cannot be empty")

        assertEquals(1, communications.contentCommunication.inputEditTextUiStateCalledList.size)
        assertEquals(
            InputEditTextUiState.Error(manageResources.string(0)),
            communications.contentCommunication.inputEditTextUiStateCalledList[0]
        )

        viewModel.changeContent("h")
        manageResources.changeExpected("This field must contain at least 5 characters")

        assertEquals(2, communications.contentCommunication.inputEditTextUiStateCalledList.size)
        assertEquals(
            InputEditTextUiState.Error(manageResources.string(0)),
            communications.contentCommunication.inputEditTextUiStateCalledList[1]
        )

        viewModel.changeContent("Bower")

        assertEquals(3, communications.contentCommunication.inputEditTextUiStateCalledList.size)
        assertEquals(
            InputEditTextUiState.NoError,
            communications.contentCommunication.inputEditTextUiStateCalledList[2]
        )

        viewModel.changeContent(
            "Ow my god, This content contains more then three hundred symbols!! " +
                    "NoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNo" +
                    "NoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNo" +
                    "NoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNo"
        )
        manageResources.changeExpected("This field must contain no more than 300 characters")

        assertEquals(4, communications.contentCommunication.inputEditTextUiStateCalledList.size)
        assertEquals(
            InputEditTextUiState.Error(manageResources.string(0)),
            communications.contentCommunication.inputEditTextUiStateCalledList[3]
        )
    }
}