package com.example.qrholder.presentation.buildQrCode

import com.example.qrholder.core.TestManageResources
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TitleTextMapperTest{

    @Test
    fun `change title input text`() {

        val manageResources = TestManageResources()
        val titleCommunication = TestInputEditTextUiStateCommunication()

        val mapper = TitleTextMapper.Base(
            manageResources,
            titleCommunication
        )

        mapper.map("")
        manageResources.changeExpected("This field cannot be empty")

        assertEquals(1, titleCommunication.inputEditTextUiStateCalledList.size)
        assertEquals(
            InputEditTextUiState.Error(manageResources.string(0)),
            titleCommunication.inputEditTextUiStateCalledList[0]
        )

        mapper.map("h")
        manageResources.changeExpected("This field must contain at least 5 characters")

        assertEquals(2, titleCommunication.inputEditTextUiStateCalledList.size)
        assertEquals(
            InputEditTextUiState.Error(manageResources.string(0)),
            titleCommunication.inputEditTextUiStateCalledList[1]
        )

        mapper.map("Bower")

        assertEquals(3, titleCommunication.inputEditTextUiStateCalledList.size)
        assertEquals(
            InputEditTextUiState.NoError,
            titleCommunication.inputEditTextUiStateCalledList[2]
        )

        mapper.map("Ow my god, This title contains more then fifty symbols")
        manageResources.changeExpected("This field must contain no more than 50 characters")

        assertEquals(4, titleCommunication.inputEditTextUiStateCalledList.size)
        assertEquals(
            InputEditTextUiState.Error(manageResources.string(0)),
            titleCommunication.inputEditTextUiStateCalledList[3]
        )
    }
}