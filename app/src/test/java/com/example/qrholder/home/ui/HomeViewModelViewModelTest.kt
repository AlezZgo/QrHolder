package com.example.qrholder.home.ui

import com.example.qrholder.core.ManageResources
import com.example.qrholder.core.TestManageResources
import com.example.qrholder.home.data.QrCodesRepositoryTest
import com.example.qrholder.home.domain.QrCode
import com.example.qrholder.home.domain.QrCodes
import com.example.qrholder.home.ui.*
import com.example.qrholder.home.ui.mapper.QrCodeToUiMapper
import com.example.qrholder.home.ui.mapper.QrCodesMapper
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class HomeViewModelViewModelTest : BaseViewModelTest() {

    private lateinit var communications: TestHomeCommunications
    private lateinit var interactor: TestHomeInteractor
    private lateinit var viewModel: HomeViewModel
    private lateinit var dispatchersList: TestDispatchersList
    private lateinit var manageResources: TestManageResources

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)

        communications = TestHomeCommunications()
        interactor = TestHomeInteractor()
        dispatchersList = TestDispatchersList()
        manageResources = TestManageResources()

        viewModel = HomeViewModel(
            dispatchers = dispatchersList,
            communications = communications,
            interactor = interactor,
            fetchAllResultMapper = QrCodesMapper(communications, QrCodeToUiMapper()),
            manageResources = manageResources
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

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
            QrCodeUiCompleteList.Success(emptyList()),
            communications.qrCodesCompleteCalledList[0]
        )

        assertEquals(2, communications.uiStateCalledList.size)
        assertEquals(HomeUiState.Loading, communications.uiStateCalledList[0])
        assertEquals(HomeUiState.Empty, communications.uiStateCalledList[1])
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
            QrCodeUiCompleteList.Error(errorMessage),
            communications.qrCodesCompleteCalledList[0]
        )

        assertEquals(2, communications.uiStateCalledList.size)
        assertEquals(HomeUiState.Loading, communications.uiStateCalledList[0])
        assertEquals(HomeUiState.Error(errorMessage), communications.uiStateCalledList[1])
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
            QrCodeUiCompleteList.Success(
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
        assertEquals(HomeUiState.Loading, communications.uiStateCalledList[0])
        assertEquals(
            HomeUiState.Success(
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

        assertEquals(1, communications.filterCalledList.size)
        assertEquals("", communications.filterCalledList[0])

        assertEquals(1, communications.qrCodesCompleteCalledList.size)
        assertEquals(
            QrCodeUiCompleteList.Success(
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
        assertEquals(HomeUiState.Loading, communications.uiStateCalledList[0])
        assertEquals(
            HomeUiState.Success(
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
            QrCodeUiCompleteList.Success(
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
            )), communications.qrCodesCompleteCalledList[0]
        )

        assertEquals(3, communications.uiStateCalledList.size)
        assertEquals(
            HomeUiState.Success(
                qrCodes = listOf(
                    QrCodeUi(
                        title = "Test title 1",
                        content = "www.something.test",
                        path = "content.cat.id1"
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
            QrCodeUiCompleteList.Success(
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
            )), communications.qrCodesCompleteCalledList[0]
        )

        assertEquals(3, communications.uiStateCalledList.size)
        assertEquals(
            HomeUiState.Success(
                qrCodes = listOf(
                    QrCodeUi(
                        title = "Test title 1",
                        content = "www.something.test",
                        path = "content.cat.id1"
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
            QrCodeUiCompleteList.Success(
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
            QrCodeUiCompleteList.Success(
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

        assertEquals(3, communications.uiStateCalledList.size)
        assertEquals(
            HomeUiState.NothingWasFound, communications.uiStateCalledList[2]
        )
    }

}

