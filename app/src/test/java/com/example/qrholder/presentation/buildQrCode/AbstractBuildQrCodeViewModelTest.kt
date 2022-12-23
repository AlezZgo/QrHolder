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
    protected lateinit var qrCodeBuiltcommunication: TestQrCodeBuiltCommunication
    protected lateinit var titleCommunication: TestValidateTitleCommunication
    protected lateinit var contentCommunication: TestValidateContentCommunication
    protected lateinit var dispatchersList: DispatchersList
    protected lateinit var repository: TestQrCodesRepository
    protected lateinit var createQrCodeImage: TestCreateQrCodeImage
    protected lateinit var validateTitle: TestValidateText
    protected lateinit var validateContent: TestValidateText
    protected lateinit var titleText: String
    protected lateinit var contentText: String

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)

        qrCodeBuiltcommunication = TestQrCodeBuiltCommunication()
        titleCommunication = TestValidateTitleCommunication()
        contentCommunication = TestValidateContentCommunication()
        dispatchersList = TestDispatchersList()
        repository = TestQrCodesRepository()
        createQrCodeImage = TestCreateQrCodeImage()
        validateTitle = TestValidateText()
        validateContent = TestValidateText()
        titleText = ""
        contentText = ""

        viewModel = BuildQrCodeViewModel(
            qrCodeBuiltCommunication = qrCodeBuiltcommunication,
            validateTitleCommunication = titleCommunication,
            validateContentCommunication = contentCommunication,
            dispatchers = dispatchersList,
            repository = repository,
            createQrCodeImage = createQrCodeImage,
            validateTitle = validateTitle,
            validateContent = validateContent,
            titleText = titleText,
            contentText = contentText
        )

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }
}