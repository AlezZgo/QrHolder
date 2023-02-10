package com.alezzgo.qrholder.presentation.buildQrCode

//internal class TitleTextMapperTest {
//
//    @Test
//    fun `change title input text`() {
//
//        val manageResources = TestManageResources()
//        val titleCommunication = TestTitleUiStateCommunication()
//
//        val mapper = TitleTextMapper.Base(
//            manageResources,
//            titleCommunication
//        )
//
//        manageResources.changeExpected("This field cannot be empty")
//        mapper.map("")
//
//        assertEquals(1, titleCommunication.inputEditTextUiStateCalledList.size)
//        assertEquals(
//            InputEditTextUi.Error(manageResources.string(0)),
//            titleCommunication.inputEditTextUiStateCalledList[0]
//        )
//
//        manageResources.changeExpected("This field must contain at least 5 characters")
//        mapper.map("h")
//
//        assertEquals(2, titleCommunication.inputEditTextUiStateCalledList.size)
//        assertEquals(
//            InputEditTextUi.Error(manageResources.string(0)),
//            titleCommunication.inputEditTextUiStateCalledList[1]
//        )
//
//        mapper.map("Bower")
//
//        assertEquals(3, titleCommunication.inputEditTextUiStateCalledList.size)
//        assertEquals(
//            InputEditTextUi.NoError,
//            titleCommunication.inputEditTextUiStateCalledList[2]
//        )
//
//        manageResources.changeExpected("This field must contain no more than 50 characters")
//        mapper.map("Ow my god, This title contains more then fifty symbols")
//
//        assertEquals(4, titleCommunication.inputEditTextUiStateCalledList.size)
//        assertEquals(
//            InputEditTextUi.Error(manageResources.string(0)),
//            titleCommunication.inputEditTextUiStateCalledList[3]
//        )
//
//        manageResources.changeExpected("This field cannot be empty")
//        mapper.map("                 ")
//
//        assertEquals(
//            5,
//            titleCommunication.inputEditTextUiStateCalledList.size
//        )
//        assertEquals(
//            InputEditTextUi.Error(manageResources.string(0)),
//            titleCommunication.inputEditTextUiStateCalledList[4]
//        )
//    }
//}