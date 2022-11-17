package com.example.qrholder.ui.home

import com.example.qrholder.ui.home.HomeViewModelTest.TestHomeCommunications.Companion.EMPTY_FIELD
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class HomeViewModelTest {

    @Test
    fun `the very first run application`() {

        //dependencies
        val communications = TestHomeCommunications()
        val interactor = TestHomeInteractor()

        //initialize
        val viewModel = HomeViewModel(communications = communications, interactor = interactor)
        interactor.changeExpectedResult(QrCodes.Success(emptyList()))

        //action
        viewModel.init(isFirstRun = true)

        //check
        assertEquals(2, communications.uiStateCalledList.size)
        assertEquals(HomeUiState.Loading, communications.uiStateCalledList[0])
        assertEquals(HomeUiState.Empty, communications.uiStateCalledList[1])

        assertEquals(1, interactor.fetchAllCalledList.size)
        assertEquals(QrCodes.Success(emptyList()), interactor.fetchAllCalledList[0])
    }

    @Test
    fun `fetch all qrCodes then re-init`() {

        val communications = TestHomeCommunications()
        val interactor = TestHomeInteractor()

        //initialize
        val viewModel = HomeViewModel(communications = communications, interactor = interactor)

        interactor.changeExpectedResult(
            QrCodes.Success(
                qrCodes = listOf<QrCode>(
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

        //action
        viewModel.init(isFirstRun = true)

        //check
        assertEquals(1, interactor.fetchAllCalledList.size)
        assertEquals(QrCodes.Success(
            qrCodes = listOf<QrCode>(
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
        ), interactor.fetchAllCalledList[0])

        //check
        assertEquals(2, communications.uiStateCalledList.size)
        assertEquals(QrCodes.Loading, communications.uiStateCalledList[0])

        assertEquals(QrCodes.Success(
            qrCodes = listOf<QrCodeUi>(
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
        ),communications.uiStateCalledList[1])

        //action
        viewModel.init(isFirstRun = false)

        //check
        assertEquals(1, interactor.resultList.size)
        assertEquals(2, communications.uiStateCalledList.size)


    }

    @Test
    fun `Enter some text in the filter input field and get list filtered then re-init`() {

        val communications = TestHomeCommunications()
        val interactor = TestHomeInteractor()

        //initialize
        val viewModel = HomeViewModel(communications, interactor)
        interactor.changeExpectedResult(QrCodes.Success(
            qrCodes = listOf<QrCode>(
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
        ))

        //action
        viewModel.init(isFirstRun = true)

        assertEquals(1, interactor.fetchAllCalledList.size)
        assertEquals(QrCodes.Success(
            qrCodes = listOf<QrCode>(
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
        ), interactor.fetchAllCalledList[0])

        //check
        assertEquals(2, communications.uiStateCalledList.size)
        assertEquals(HomeUiState.Loading, communications.uiStateCalledList[0])
        assertEquals(HomeUiState.Success(
            qrCodes = listOf<QrCodeUi>(
                QrCodeUi(
                    title = "Test title 1",
                    content = "www.something.test"
                ),
                QrCodeUi(
                    title = "Test title 2",
                    content = "www.something.test"
                ),
                QrCodeUi(
                    title = "Test title 3",
                    content = "www.something.test"
                )
            )
        ), communications.uiStateCalledList[1])

        //action
        val testFilter = "title 1"
        viewModel.filter(testFilter)

        //check
        assertEquals(1, interactor.fetchAllCalledList.size)

        assertEquals(3, communications.uiStateCalledList.size)
        assertEquals(HomeUiState.Success(
            qrCodes = listOf<QrCodeUi>(
                QrCodeUi(
                    title = "Test title 1",
                    content = "www.something.test"
                )
            )
        ), communications.uiStateCalledList[2])

        //check
        assertEquals(1, communications.filterCalledList.size)
        assertEquals(EMPTY_FIELD, communications.filterCalledList[0])

        //action
        viewModel.init(isFirstRun = false)

        //check
        assertEquals(1, interactor.fetchAllCalledList.size)

        assertEquals(3, communications.uiStateCalledList.size)
        assertEquals(HomeUiState.Success(
            qrCodes = listOf<QrCodeUi>(
                QrCodeUi(
                    title = "Test title 1",
                    content = "www.something.test"
                )
            )
        ), communications.uiStateCalledList[2])

    }

    @Test
    fun `Enter some text in the filter input field but nothing was found`() {
        val communications = TestHomeCommunications()
        val interactor = TestHomeInteractor()

        //initialize
        val viewModel = HomeViewModel(communications, interactor)
        interactor.changeExpectedResult(QrCodes.Success(
            qrCodes = listOf<QrCode>(
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
        ))

        //action
        viewModel.init(isFirstRun = true)

        //check
        assertEquals(1, interactor.fetchAllCalledList.size)
        assertEquals(QrCodes.Success(
            qrCodes = listOf<QrCode>(
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
        ), interactor.fetchAllCalledList[0])

        //check
        assertEquals(2, communications.uiStateCalledList.size)
        assertEquals(HomeUiState.Loading, communications.uiStateCalledList[0])
        assertEquals(HomeUiState.Success(
            qrCodes = listOf<QrCodeUi>(
                QrCodeUi(
                    title = "Test title 1",
                    content = "www.something.test"
                ),
                QrCodeUi(
                    title = "Test title 2",
                    content = "www.something.test"
                ),
                QrCodeUi(
                    title = "Test title 3",
                    content = "www.something.test"
                )
            )
        ), communications.uiStateCalledList[1])

        //action
        val testFilter = "efjlsiejfso"
        viewModel.filter(testFilter)

        //check
        assertEquals(1, interactor.fetchAllCalledList.size)

        assertEquals(2, communications.filterCalledList.size)
        assertEquals(testFilter, communications.filterCalledList[1])

        assertEquals(3, communications.uiStateCalledList.size)
        assertEquals(HomeUiState.NothingWasFound, communications.uiStateCalledList[2])

        //action
        viewModel.init(isFirstRun = false)

        //check
        assertEquals(1, interactor.fetchAllCalledList.size)

        assertEquals(2, communications.filterCalledList.size)
        assertEquals(testFilter, communications.filterCalledList[1])

        assertEquals(3, communications.uiStateCalledList.size)
        assertEquals(HomeUiState.NothingWasFound, communications.uiStateCalledList[2])
    }


    private class TestHomeInteractor : HomeInteractor {

        private var result: QrCodes = QrCodes.Success(emptyList())

        val fetchAllCalledList = mutableListOf<QrCodes>()

        fun changeExpectedResult(qrCodes: QrCodes) {
            result = qrCodes
        }

        override suspend fun fetchAll(filter: String) {
            fetchAllCalledList.add(result)
        }
    }

    private class TestHomeCommunications : HomeCommunications {

        val uiStateCalledList = mutableListOf<HomeUiState>()
        val filterCalledList = mutableListOf(EMPTY_FIELD)

        override fun showState(state: HomeUiState) {
            uiStateCalledList.add(state)
        }

        override fun filter(text: String) {
            filterCalledList.add(text)
        }

        companion object {
            const val EMPTY_FIELD = ""
        }

    }
}

