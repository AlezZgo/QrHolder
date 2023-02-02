package com.example.qrholder.presentation.home.ui

import com.example.qrholder.core.TestDispatchersList
import com.example.qrholder.core.TestManageResources
import com.example.qrholder.core.TestQrCodesInteractor
import com.example.qrholder.presentation.home.HomeViewModel
import com.example.qrholder.presentation.home.mapper.QrCodeToUiMapper
import com.example.qrholder.presentation.home.mapper.QrCodesMapper
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

abstract class AbstractHomeViewModelViewModelTest {
    protected lateinit var communications: TestHomeCommunications
    protected lateinit var interactor: TestQrCodesInteractor
    protected lateinit var mapper: QrCodesMapper
    protected lateinit var toUiMapper: QrCodeToUiMapper
    protected lateinit var dispatchersList: TestDispatchersList
    protected lateinit var manageResources: TestManageResources

    protected lateinit var viewModel: HomeViewModel

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)

        communications = TestHomeCommunications()
        interactor = TestQrCodesInteractor()
        toUiMapper = QrCodeToUiMapper()
        mapper = QrCodesMapper(communications, toUiMapper)
        dispatchersList = TestDispatchersList()
        manageResources = TestManageResources()

        viewModel = HomeViewModel(
            dispatchers = dispatchersList,
            communications = communications,
            interactor = interactor,
            fetchAllResultMapper = mapper,
            manageResources = manageResources,
            qrCodeToUiMapper = toUiMapper
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }
}