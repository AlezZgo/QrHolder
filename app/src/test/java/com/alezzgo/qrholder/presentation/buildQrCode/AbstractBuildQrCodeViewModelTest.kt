package com.alezzgo.qrholder.presentation.buildQrCode

import com.alezzgo.qrholder.core.ManageResources
import com.alezzgo.qrholder.core.TestDispatchersList
import com.alezzgo.qrholder.core.TestManageResources
import com.alezzgo.qrholder.data.mapper.QrCodeToDataMapper
import com.alezzgo.qrholder.presentation.core.viewmodel.DispatchersList
import com.alezzgo.qrholder.presentation.home.domain.TestQrCodesRepository
import com.alezzgo.qrholder.presentation.home.mapper.QrCodeToUiMapper
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
    protected lateinit var qrCodeBuiltCommunication: TestQrCodeBuiltCommunication
    protected lateinit var titleCommunication: TestValidateTitleCommunication
    protected lateinit var contentCommunication: TestValidateContentCommunication
    protected lateinit var dispatchersList: DispatchersList
    protected lateinit var repository: TestQrCodesRepository
    protected lateinit var createQrCodeImage: TestCreateQrCodeImage
    protected lateinit var qrCodeToUiMapper: QrCodeToUiMapper
    protected lateinit var qrCodeToDataMapper: QrCodeToDataMapper
    protected lateinit var manageResources: ManageResources
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

        qrCodeBuiltCommunication = TestQrCodeBuiltCommunication()
        titleCommunication = TestValidateTitleCommunication()
        contentCommunication = TestValidateContentCommunication()
        dispatchersList = TestDispatchersList()
        repository = TestQrCodesRepository()
        createQrCodeImage = TestCreateQrCodeImage()
        qrCodeToUiMapper = QrCodeToUiMapper()
        qrCodeToDataMapper = QrCodeToDataMapper()
        manageResources = TestManageResources()
        validateTitle = TestValidateText()
        validateContent = TestValidateText()
        titleText = ""
        contentText = ""

        viewModel = BuildQrCodeViewModel(
            qrCodeBuiltCommunication = qrCodeBuiltCommunication,
            validateTitleCommunication = titleCommunication,
            validateContentCommunication = contentCommunication,
            dispatchers = dispatchersList,
            repository = repository,
            createQrCodeImage = createQrCodeImage,
            qrCodeToUiMapper = qrCodeToUiMapper,
            qrCodeToDataMapper = qrCodeToDataMapper,
            manageResources = manageResources,
            validateTitle = validateTitle,
            validateContent = validateContent,
            titleText = titleText,
            contentText = contentText,
        )

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }
}