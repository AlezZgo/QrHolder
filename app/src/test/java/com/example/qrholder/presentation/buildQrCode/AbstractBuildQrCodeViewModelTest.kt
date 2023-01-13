package com.example.qrholder.presentation.buildQrCode

import com.example.qrholder.core.TestDispatchersList
import com.example.qrholder.presentation.core.viewmodel.DispatchersList
import com.example.qrholder.presentation.home.domain.TestQrCodesRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

abstract class AbstractBuildQrCodeViewModelTest {

    protected lateinit var viewModel: BuildQrCodeViewModel
    protected lateinit var communications: TestBuildQrCodeCommunications
    protected lateinit var qrCodeInBuild: QrCodeInBuild
    protected lateinit var createQrCodeImage: TestCreateQrCodeImage
    protected lateinit var qrCodesRepository: TestQrCodesRepository
    protected lateinit var dispatchersList: DispatchersList

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)

        communications = TestBuildQrCodeCommunications()
        qrCodeInBuild = QrCodeInBuild("", "")
        createQrCodeImage = TestCreateQrCodeImage()
        qrCodesRepository = TestQrCodesRepository()
        dispatchersList = TestDispatchersList()

        viewModel = BuildQrCodeViewModel(
            communications = communications,
            qrCodeInBuild = qrCodeInBuild,
            createQrCodeImage = createQrCodeImage,
            repository = qrCodesRepository,
            dispatchers = dispatchersList,
        )

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }
}