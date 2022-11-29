package com.example.qrholder.home

import com.example.qrholder.home.domain.QrCode
import com.example.qrholder.home.domain.QrCodes
import com.example.qrholder.home.ui.HomeUiState
import com.example.qrholder.home.ui.QrCodeUi
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class HomeViewModelTest : BaseTest() {

    @Test
    fun `the very first run application`() {

        interactor.changeExpectedResult(QrCodes.Success(emptyList()))

        viewModel.init(isFirstRun = true)

        assertEquals(1, interactor.fetchAllCalledList.size)
        assertEquals(QrCodes.Success(emptyList()), interactor.fetchAllCalledList[0])

        assertEquals(1, communications.filterCalledList.size)
        assertEquals("", communications.filterCalledList[0])

        assertEquals(1, communications.qrCodesCompleteCalledList.size)
        assertEquals(emptyList<QrCodeUi>(), communications.qrCodesCompleteCalledList[0])

        assertEquals(2, communications.uiStateCalledList.size)
        assertEquals(HomeUiState.Loading, communications.uiStateCalledList[0])
        assertEquals(HomeUiState.Empty, communications.uiStateCalledList[1])
    }

    @Test
    fun `Run application with error`() {
        val errorMessage = "Something went wrong"
        interactor.changeExpectedResult(QrCodes.Failure(message = errorMessage))

        viewModel.init(isFirstRun = true)

        assertEquals(1, interactor.fetchAllCalledList.size)
        assertEquals(QrCodes.Failure(message = errorMessage), interactor.fetchAllCalledList[0])

        assertEquals(1, communications.filterCalledList.size)
        assertEquals("", communications.filterCalledList[0])

        assertEquals(1, communications.qrCodesCompleteCalledList.size)
        assertEquals(emptyList<QrCodeUi>(), communications.qrCodesCompleteCalledList[0])

        assertEquals(2, communications.uiStateCalledList.size)
        assertEquals(HomeUiState.Loading, communications.uiStateCalledList[0])
        assertEquals(HomeUiState.Error(errorMessage), communications.uiStateCalledList[1])
    }

    @Test
    fun `fetch all qrCodes without filter then re-init`() {

        interactor.changeExpectedResult(
            QrCodes.Success(
                qrCodes = listOf(
                    QrCode(
                        title = "Test title 1",
                        content = "www.something.test"
                    ),
                    QrCode(
                        title = "Test title 2",
                        content = "www.something.test"
                    ),
                    QrCode(
                        title = "Test title 3",
                        content = "www.something.test"
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
                        content = "www.something.test"
                    ),
                    QrCode(
                        title = "Test title 2",
                        content = "www.something.test"
                    ),
                    QrCode(
                        title = "Test title 3",
                        content = "www.something.test"
                    )
                )
            ), interactor.fetchAllCalledList[0]
        )

        assertEquals(1, communications.filterCalledList.size)
        assertEquals("", communications.filterCalledList[0])

        assertEquals(1, communications.qrCodesCompleteCalledList.size)
        assertEquals(
            listOf(
                QrCodeUi(
                    title = "Test title 1",
                    content = "www.something.test"
                ),
                QrCodeUi(
                    title = "Test title 1",
                    content = "www.something.test"
                ),
                QrCodeUi(
                    title = "Test title 1",
                    content = "www.something.test"
                ),
            ), communications.qrCodesCompleteCalledList[0]
        )

        assertEquals(2, communications.uiStateCalledList.size)
        assertEquals(HomeUiState.Loading, communications.uiStateCalledList[0])
        assertEquals(
            HomeUiState.Success(
                qrCodes = listOf(
                    QrCodeUi(
                        title = "Test title 1",
                        content = "www.something.test"
                    ),
                    QrCodeUi(
                        title = "Test title 1",
                        content = "www.something.test"
                    ),
                    QrCodeUi(
                        title = "Test title 1",
                        content = "www.something.test"
                    ),
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
                        content = "www.something.test"
                    ),
                    QrCode(
                        title = "Test title 2",
                        content = "www.something.test"
                    ),
                    QrCode(
                        title = "Test title 3",
                        content = "www.something.test"
                    )
                )
            ), interactor.fetchAllCalledList[0]
        )

        assertEquals(1, communications.filterCalledList.size)
        assertEquals("", communications.filterCalledList[0])

        assertEquals(1, communications.qrCodesCompleteCalledList.size)
        assertEquals(
            listOf(
                QrCodeUi(
                    title = "Test title 1",
                    content = "www.something.test"
                ),
                QrCodeUi(
                    title = "Test title 1",
                    content = "www.something.test"
                ),
                QrCodeUi(
                    title = "Test title 1",
                    content = "www.something.test"
                ),
            ), communications.qrCodesCompleteCalledList[0]
        )

        assertEquals(2, communications.uiStateCalledList.size)
        assertEquals(HomeUiState.Loading, communications.uiStateCalledList[0])
        assertEquals(
            HomeUiState.Success(
                qrCodes = listOf(
                    QrCodeUi(
                        title = "Test title 1",
                        content = "www.something.test"
                    ),
                    QrCodeUi(
                        title = "Test title 1",
                        content = "www.something.test"
                    ),
                    QrCodeUi(
                        title = "Test title 1",
                        content = "www.something.test"
                    ),
                )
            ), communications.uiStateCalledList[1]
        )
    }

    @Test
    fun `Enter some text in the filter input field and get list filtered then re-init`() {

        interactor.changeExpectedResult(
            QrCodes.Success(
                qrCodes = listOf(
                    QrCode(
                        title = "Test title 1",
                        content = "www.something.test"
                    ),
                    QrCode(
                        title = "Test title 2",
                        content = "www.something.test"
                    ),
                    QrCode(
                        title = "Test title 3",
                        content = "www.something.test"
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
                        content = "www.something.test"
                    ),
                    QrCode(
                        title = "Test title 2",
                        content = "www.something.test"
                    ),
                    QrCode(
                        title = "Test title 3",
                        content = "www.something.test"
                    )
                )
            ), interactor.fetchAllCalledList[0]
        )

        assertEquals(2, communications.filterCalledList.size)
        assertEquals("", communications.filterCalledList[0])
        assertEquals(testFilter, communications.filterCalledList[1])

        assertEquals(1, communications.qrCodesCompleteCalledList.size)
        assertEquals(
            listOf(
                QrCodeUi(
                    title = "Test title 1",
                    content = "www.something.test"
                ),
                QrCodeUi(
                    title = "Test title 1",
                    content = "www.something.test"
                ),
                QrCodeUi(
                    title = "Test title 1",
                    content = "www.something.test"
                ),
            ), communications.qrCodesCompleteCalledList[0]
        )

        assertEquals(3, communications.uiStateCalledList.size)
        assertEquals(
            HomeUiState.Success(
                qrCodes = listOf(
                    QrCodeUi(
                        title = "Test title 1",
                        content = "www.something.test"
                    )
                )
            ), communications.uiStateCalledList[2]
        )

        //action
        viewModel.init(isFirstRun = false)

        assertEquals(1, interactor.fetchAllCalledList.size)
        assertEquals(
            QrCodes.Success(
                qrCodes = listOf(
                    QrCode(
                        title = "Test title 1",
                        content = "www.something.test"
                    ),
                    QrCode(
                        title = "Test title 2",
                        content = "www.something.test"
                    ),
                    QrCode(
                        title = "Test title 3",
                        content = "www.something.test"
                    )
                )
            ), interactor.fetchAllCalledList[0]
        )

        assertEquals(2, communications.filterCalledList.size)
        assertEquals("", communications.filterCalledList[0])
        assertEquals(testFilter, communications.filterCalledList[1])

        assertEquals(1, communications.qrCodesCompleteCalledList.size)
        assertEquals(
            listOf(
                QrCodeUi(
                    title = "Test title 1",
                    content = "www.something.test"
                ),
                QrCodeUi(
                    title = "Test title 1",
                    content = "www.something.test"
                ),
                QrCodeUi(
                    title = "Test title 1",
                    content = "www.something.test"
                ),
            ), communications.qrCodesCompleteCalledList[0]
        )

        assertEquals(3, communications.uiStateCalledList.size)
        assertEquals(
            HomeUiState.Success(
                qrCodes = listOf(
                    QrCodeUi(
                        title = "Test title 1",
                        content = "www.something.test"
                    )
                )
            ), communications.uiStateCalledList[2]
        )
    }

    @Test
    fun `Enter some text in the filter input field but nothing was found then re-init`() {

        interactor.changeExpectedResult(
            QrCodes.Success(
                qrCodes = listOf(
                    QrCode(
                        title = "Test title 1",
                        content = "www.something.test"
                    ),
                    QrCode(
                        title = "Test title 2",
                        content = "www.something.test"
                    ),
                    QrCode(
                        title = "Test title 3",
                        content = "www.something.test"
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
                        content = "www.something.test"
                    ),
                    QrCode(
                        title = "Test title 2",
                        content = "www.something.test"
                    ),
                    QrCode(
                        title = "Test title 3",
                        content = "www.something.test"
                    )
                )
            ), interactor.fetchAllCalledList[0]
        )

        assertEquals(2, communications.filterCalledList.size)
        assertEquals("", communications.filterCalledList[0])
        assertEquals(testFilter, communications.filterCalledList[1])

        assertEquals(1, communications.qrCodesCompleteCalledList.size)
        assertEquals(
            listOf(
                QrCodeUi(
                    title = "Test title 1",
                    content = "www.something.test"
                ),
                QrCodeUi(
                    title = "Test title 1",
                    content = "www.something.test"
                ),
                QrCodeUi(
                    title = "Test title 1",
                    content = "www.something.test"
                ),
            ), communications.qrCodesCompleteCalledList[0]
        )

        assertEquals(3, communications.uiStateCalledList.size)
        assertEquals(
            HomeUiState.NothingWasFound, communications.uiStateCalledList[2]
        )

        viewModel.init(isFirstRun = false)

        assertEquals(1, interactor.fetchAllCalledList.size)
        assertEquals(
            QrCodes.Success(
                qrCodes = listOf(
                    QrCode(
                        title = "Test title 1",
                        content = "www.something.test"
                    ),
                    QrCode(
                        title = "Test title 2",
                        content = "www.something.test"
                    ),
                    QrCode(
                        title = "Test title 3",
                        content = "www.something.test"
                    )
                )
            ), interactor.fetchAllCalledList[0]
        )

        assertEquals(2, communications.filterCalledList.size)
        assertEquals("", communications.filterCalledList[0])
        assertEquals(testFilter, communications.filterCalledList[1])

        assertEquals(1, communications.qrCodesCompleteCalledList.size)
        assertEquals(
            listOf(
                QrCodeUi(
                    title = "Test title 1",
                    content = "www.something.test"
                ),
                QrCodeUi(
                    title = "Test title 1",
                    content = "www.something.test"
                ),
                QrCodeUi(
                    title = "Test title 1",
                    content = "www.something.test"
                ),
            ), communications.qrCodesCompleteCalledList[0]
        )

        assertEquals(3, communications.uiStateCalledList.size)
        assertEquals(
            HomeUiState.NothingWasFound, communications.uiStateCalledList[2]
        )
    }

}

