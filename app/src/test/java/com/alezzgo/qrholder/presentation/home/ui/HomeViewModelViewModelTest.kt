package com.alezzgo.qrholder.presentation.home.ui

import com.alezzgo.qrholder.domain.model.QrCode
import com.alezzgo.qrholder.domain.model.QrCodes
import com.alezzgo.qrholder.presentation.core.model.QrCodeUi
import com.alezzgo.qrholder.presentation.home.model.HomeUi
import com.alezzgo.qrholder.presentation.home.model.QrCodeCompleteListUi
import kotlinx.coroutines.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class HomeViewModelViewModelTest : AbstractHomeViewModelViewModelTest() {

    @Test
    fun `the very first run application`() = runBlocking {

        manageResources.changeExpected("")
        interactor.changeExpectedResult(QrCodes.Success(emptyList()))

        viewModel.init(isFirstRun = true)

        assertEquals(1, interactor.fetchAllCalledList.size)
        assertEquals(QrCodes.Success(emptyList()), interactor.fetchAllCalledList[0])

        assertEquals(1, communications.filterCalledList.size)
        assertEquals("", communications.filterCalledList[0])

        assertEquals(1, communications.qrCodesCompleteCalledList.size)
        assertEquals(
            QrCodeCompleteListUi.Success(emptyList()),
            communications.qrCodesCompleteCalledList[0]
        )

        assertEquals(1, communications.uiStateCalledList.size)
//        assertEquals(HomeUi.Loading, communications.uiStateCalledList[0])
        assertEquals(HomeUi.Empty, communications.uiStateCalledList[0])
    }

    @Test
    fun `Run application with error`() = runBlocking {
        val errorMessage = "Something went wrong"
        interactor.changeExpectedResult(QrCodes.Failure(message = errorMessage))

        viewModel.init(isFirstRun = true)

        assertEquals(1, interactor.fetchAllCalledList.size)
        assertEquals(QrCodes.Failure(message = errorMessage), interactor.fetchAllCalledList[0])

        assertEquals(1, communications.filterCalledList.size)
        assertEquals("", communications.filterCalledList[0])

        assertEquals(1, communications.qrCodesCompleteCalledList.size)
        assertEquals(
            QrCodeCompleteListUi.Error(errorMessage),
            communications.qrCodesCompleteCalledList[0]
        )

        assertEquals(1, communications.uiStateCalledList.size)
//        assertEquals(HomeUi.Loading, communications.uiStateCalledList[0])
        assertEquals(HomeUi.Error(errorMessage), communications.uiStateCalledList[0])
    }

    @Test
    fun `fetch all qrCodes without filter then re-init`() = runBlocking {

        interactor.changeExpectedResult(
            QrCodes.Success(
                qrCodes = listOf(
                    QrCode(
                        title = "Test title 1",
                        content = "www.something.test",
                        path = "content.cat.id1"
                    ),
                    QrCode(
                        title = "Test title 2",
                        content = "www.something.test",
                        path = "content.cat.id2"
                    ),
                    QrCode(
                        title = "Test title 3",
                        content = "www.something.test",
                        path = "content.cat.id3"
                    )
                )
            )
        )

        viewModel.init(isFirstRun = true)

        assertEquals(1, interactor.fetchAllCalledList.size)
        assertEquals(
            QrCodes.Success(
                qrCodes = listOf(
                    QrCode(
                        title = "Test title 1",
                        content = "www.something.test",
                        path = "content.cat.id1"
                    ),
                    QrCode(
                        title = "Test title 2",
                        content = "www.something.test",
                        path = "content.cat.id2"
                    ),
                    QrCode(
                        title = "Test title 3",
                        content = "www.something.test",
                        path = "content.cat.id3"
                    )
                )
            ), interactor.fetchAllCalledList[0]
        )

        assertEquals(1, communications.filterCalledList.size)
        assertEquals("", communications.filterCalledList[0])

        assertEquals(1, communications.qrCodesCompleteCalledList.size)
        assertEquals(
            QrCodeCompleteListUi.Success(
                listOf(
                    QrCodeUi(
                        title = "Test title 1",
                        content = "www.something.test",
                        path = "content.cat.id1"
                    ),
                    QrCodeUi(
                        title = "Test title 2",
                        content = "www.something.test",
                        path = "content.cat.id2"
                    ),
                    QrCodeUi(
                        title = "Test title 3",
                        content = "www.something.test",
                        path = "content.cat.id3"
                    ),
                )
            ), communications.qrCodesCompleteCalledList[0]
        )

        assertEquals(1, communications.uiStateCalledList.size)
//        assertEquals(HomeUi.Loading, communications.uiStateCalledList[0])
        assertEquals(
            HomeUi.Success(
                qrCodes = listOf(
                    QrCodeUi(
                        title = "Test title 1",
                        content = "www.something.test",
                        path = "content.cat.id1"
                    ),
                    QrCodeUi(
                        title = "Test title 2",
                        content = "www.something.test",
                        path = "content.cat.id2"
                    ),
                    QrCodeUi(
                        title = "Test title 3",
                        content = "www.something.test",
                        path = "content.cat.id3"
                    ),
                )
            ), communications.uiStateCalledList[0]
        )

        //action
        viewModel.init(isFirstRun = false)

        assertEquals(1, interactor.fetchAllCalledList.size)
        assertEquals(
            QrCodes.Success(
                qrCodes = listOf(
                    QrCode(
                        title = "Test title 1",
                        content = "www.something.test",
                        path = "content.cat.id1"
                    ),
                    QrCode(
                        title = "Test title 2",
                        content = "www.something.test",
                        path = "content.cat.id2"
                    ),
                    QrCode(
                        title = "Test title 3",
                        content = "www.something.test",
                        path = "content.cat.id3"
                    )
                )
            ), interactor.fetchAllCalledList[0]
        )

        assertEquals(1, communications.filterCalledList.size)
        assertEquals("", communications.filterCalledList[0])

        assertEquals(1, communications.qrCodesCompleteCalledList.size)
        assertEquals(
            QrCodeCompleteListUi.Success(
                listOf(
                    QrCodeUi(
                        title = "Test title 1",
                        content = "www.something.test",
                        path = "content.cat.id1"
                    ),
                    QrCodeUi(
                        title = "Test title 2",
                        content = "www.something.test",
                        path = "content.cat.id2"
                    ),
                    QrCodeUi(
                        title = "Test title 3",
                        content = "www.something.test",
                        path = "content.cat.id3"
                    ),
                )
            ), communications.qrCodesCompleteCalledList[0]
        )

        assertEquals(1, communications.uiStateCalledList.size)
//        assertEquals(HomeUi.Loading, communications.uiStateCalledList[0])
        assertEquals(
            HomeUi.Success(
                qrCodes = listOf(
                    QrCodeUi(
                        title = "Test title 1",
                        content = "www.something.test",
                        path = "content.cat.id1"
                    ),
                    QrCodeUi(
                        title = "Test title 2",
                        content = "www.something.test",
                        path = "content.cat.id2"
                    ),
                    QrCodeUi(
                        title = "Test title 3",
                        content = "www.something.test",
                        path = "content.cat.id3"
                    ),
                )
            ), communications.uiStateCalledList[0]
        )
    }

    @Test
    fun `Enter some text in the filter input field and get list filtered then re-init`() {

        interactor.changeExpectedResult(
            QrCodes.Success(
                qrCodes = listOf(
                    QrCode(
                        title = "Test title 1",
                        content = "www.something.test",
                        path = "content.cat.id1"
                    ),
                    QrCode(
                        title = "Test title 2",
                        content = "www.something.test",
                        path = "content.cat.id2"
                    ),
                    QrCode(
                        title = "Test title 3",
                        content = "www.something.test",
                        path = "content.cat.id3"
                    )
                )
            )
        )

        viewModel.init(isFirstRun = true)

        val testFilter = "title 1"
        viewModel.filter(testFilter)

        assertEquals(1, interactor.fetchAllCalledList.size)
        assertEquals(
            QrCodes.Success(
                qrCodes = listOf(
                    QrCode(
                        title = "Test title 1",
                        content = "www.something.test",
                        path = "content.cat.id1"
                    ),
                    QrCode(
                        title = "Test title 2",
                        content = "www.something.test",
                        path = "content.cat.id2"
                    ),
                    QrCode(
                        title = "Test title 3",
                        content = "www.something.test",
                        path = "content.cat.id3"
                    )
                )
            ), interactor.fetchAllCalledList[0]
        )

        assertEquals(2, communications.filterCalledList.size)
        assertEquals("", communications.filterCalledList[0])
        assertEquals(testFilter, communications.filterCalledList[1])

        assertEquals(1, communications.qrCodesCompleteCalledList.size)
        assertEquals(
            QrCodeCompleteListUi.Success(
                listOf(
                    QrCodeUi(
                        title = "Test title 1",
                        content = "www.something.test",
                        path = "content.cat.id1"
                    ),
                    QrCodeUi(
                        title = "Test title 2",
                        content = "www.something.test",
                        path = "content.cat.id2"
                    ),
                    QrCodeUi(
                        title = "Test title 3",
                        content = "www.something.test",
                        path = "content.cat.id3"
                    ),
                )
            ), communications.qrCodesCompleteCalledList[0]
        )

        assertEquals(2, communications.uiStateCalledList.size)
        assertEquals(
            HomeUi.Success(
                qrCodes = listOf(
                    QrCodeUi(
                        title = "Test title 1",
                        content = "www.something.test",
                        path = "content.cat.id1"
                    )
                )
            ), communications.uiStateCalledList[1]
        )

        //action
        viewModel.init(isFirstRun = false)

        assertEquals(1, interactor.fetchAllCalledList.size)
        assertEquals(
            QrCodes.Success(
                qrCodes = listOf(
                    QrCode(
                        title = "Test title 1",
                        content = "www.something.test",
                        path = "content.cat.id1"
                    ),
                    QrCode(
                        title = "Test title 2",
                        content = "www.something.test",
                        path = "content.cat.id2"
                    ),
                    QrCode(
                        title = "Test title 3",
                        content = "www.something.test",
                        path = "content.cat.id3"
                    )
                )
            ), interactor.fetchAllCalledList[0]
        )

        assertEquals(2, communications.filterCalledList.size)
        assertEquals("", communications.filterCalledList[0])
        assertEquals(testFilter, communications.filterCalledList[1])

        assertEquals(1, communications.qrCodesCompleteCalledList.size)
        assertEquals(
            QrCodeCompleteListUi.Success(
                listOf(
                    QrCodeUi(
                        title = "Test title 1",
                        content = "www.something.test",
                        path = "content.cat.id1"
                    ),
                    QrCodeUi(
                        title = "Test title 2",
                        content = "www.something.test",
                        path = "content.cat.id2"
                    ),
                    QrCodeUi(
                        title = "Test title 3",
                        content = "www.something.test",
                        path = "content.cat.id3"
                    ),
                )
            ), communications.qrCodesCompleteCalledList[0]
        )

        assertEquals(2, communications.uiStateCalledList.size)
        assertEquals(
            HomeUi.Success(
                qrCodes = listOf(
                    QrCodeUi(
                        title = "Test title 1",
                        content = "www.something.test",
                        path = "content.cat.id1"
                    )
                )
            ), communications.uiStateCalledList[1]
        )
    }

    @Test
    fun `Enter some text in the filter input field but nothing was found then re-init`() {

        interactor.changeExpectedResult(
            QrCodes.Success(
                qrCodes = listOf(
                    QrCode(
                        title = "Test title 1",
                        content = "www.something.test",
                        path = "content.cat.id1"
                    ),
                    QrCode(
                        title = "Test title 2",
                        content = "www.something.test",
                        path = "content.cat.id2"
                    ),
                    QrCode(
                        title = "Test title 3",
                        content = "www.something.test",
                        path = "content.cat.id3"
                    )
                )
            )
        )

        viewModel.init(isFirstRun = true)

        val testFilter = "sejoifjsoefjsoeifjsoeijf"
        viewModel.filter(testFilter)

        assertEquals(1, interactor.fetchAllCalledList.size)
        assertEquals(
            QrCodes.Success(
                qrCodes = listOf(
                    QrCode(
                        title = "Test title 1",
                        content = "www.something.test",
                        path = "content.cat.id1"
                    ),
                    QrCode(
                        title = "Test title 2",
                        content = "www.something.test",
                        path = "content.cat.id2"
                    ),
                    QrCode(
                        title = "Test title 3",
                        content = "www.something.test",
                        path = "content.cat.id3"
                    )
                )
            ), interactor.fetchAllCalledList[0]
        )

        assertEquals(2, communications.filterCalledList.size)
        assertEquals("", communications.filterCalledList[0])
        assertEquals(testFilter, communications.filterCalledList[1])

        assertEquals(1, communications.qrCodesCompleteCalledList.size)
        assertEquals(
            QrCodeCompleteListUi.Success(
                listOf(
                    QrCodeUi(
                        title = "Test title 1",
                        content = "www.something.test",
                        path = "content.cat.id1"
                    ),
                    QrCodeUi(
                        title = "Test title 2",
                        content = "www.something.test",
                        path = "content.cat.id2"
                    ),
                    QrCodeUi(
                        title = "Test title 3",
                        content = "www.something.test",
                        path = "content.cat.id3"
                    ),
                )
            ), communications.qrCodesCompleteCalledList[0]
        )

        assertEquals(2, communications.uiStateCalledList.size)
        assertEquals(
            HomeUi.NothingWasFound, communications.uiStateCalledList[1]
        )

        viewModel.init(isFirstRun = false)

        assertEquals(1, interactor.fetchAllCalledList.size)
        assertEquals(
            QrCodes.Success(
                qrCodes = listOf(
                    QrCode(
                        title = "Test title 1",
                        content = "www.something.test",
                        path = "content.cat.id1"
                    ),
                    QrCode(
                        title = "Test title 2",
                        content = "www.something.test",
                        path = "content.cat.id2"
                    ),
                    QrCode(
                        title = "Test title 3",
                        content = "www.something.test",
                        path = "content.cat.id3"
                    )
                )
            ), interactor.fetchAllCalledList[0]
        )

        assertEquals(2, communications.filterCalledList.size)
        assertEquals("", communications.filterCalledList[0])
        assertEquals(testFilter, communications.filterCalledList[1])

        assertEquals(1, communications.qrCodesCompleteCalledList.size)
        assertEquals(
            QrCodeCompleteListUi.Success(
                listOf(
                    QrCodeUi(
                        title = "Test title 1",
                        content = "www.something.test",
                        path = "content.cat.id1"
                    ),
                    QrCodeUi(
                        title = "Test title 2",
                        content = "www.something.test",
                        path = "content.cat.id2"
                    ),
                    QrCodeUi(
                        title = "Test title 3",
                        content = "www.something.test",
                        path = "content.cat.id3"
                    ),
                )
            ), communications.qrCodesCompleteCalledList[0]
        )

        assertEquals(2, communications.uiStateCalledList.size)
        assertEquals(
            HomeUi.NothingWasFound, communications.uiStateCalledList[1]
        )
    }

}

