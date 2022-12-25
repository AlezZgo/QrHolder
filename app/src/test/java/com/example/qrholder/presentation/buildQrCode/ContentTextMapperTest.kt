package com.example.qrholder.presentation.buildQrCode

import com.example.qrholder.core.TestManageResources
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class ContentTextMapperTest {

    @Test
    fun `change content input text`() {

        val manageResources = TestManageResources()
        val contentCommunication = TestInputEditTextUiStateCommunication()

        val mapper = ContentTextMapper.Base(
            manageResources,
            contentCommunication
        )

        mapper.map("")
        manageResources.changeExpected("This field cannot be empty")

        Assertions.assertEquals(
            1,
            contentCommunication.inputEditTextUiStateCalledList.size
        )
        Assertions.assertEquals(
            InputEditTextUiState.Error(manageResources.string(0)),
            contentCommunication.inputEditTextUiStateCalledList[0]
        )

        mapper.map("h")
        manageResources.changeExpected("This field must contain at least 5 characters")

        Assertions.assertEquals(
            2,
            contentCommunication.inputEditTextUiStateCalledList.size
        )
        Assertions.assertEquals(
            InputEditTextUiState.Error(manageResources.string(0)),
            contentCommunication.inputEditTextUiStateCalledList[1]
        )

        mapper.map("Bower")

        Assertions.assertEquals(
            3,
            contentCommunication.inputEditTextUiStateCalledList.size
        )
        Assertions.assertEquals(
            InputEditTextUiState.NoError,
            contentCommunication.inputEditTextUiStateCalledList[2]
        )

        mapper.map(
            "Ow my god, This content contains more then three hundred symbols!! " +
                    "NoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNo" +
                    "NoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNo" +
                    "NoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNoNo"
        )
        manageResources.changeExpected("This field must contain no more than 300 characters")

        Assertions.assertEquals(
            4,
            contentCommunication.inputEditTextUiStateCalledList.size
        )
        Assertions.assertEquals(
            InputEditTextUiState.Error(manageResources.string(0)),
            contentCommunication.inputEditTextUiStateCalledList[3]
        )
    }
}