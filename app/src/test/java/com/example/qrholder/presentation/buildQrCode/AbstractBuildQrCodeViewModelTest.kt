package com.example.qrholder.presentation.buildQrCode

import com.example.qrholder.core.ManageResources
import com.example.qrholder.core.TestDispatchersList
import com.example.qrholder.core.TestManageResources
import com.example.qrholder.core.TestQrCodesInteractor
import com.example.qrholder.presentation.core.viewmodel.DispatchersList
import com.example.qrholder.presentation.home.HomeViewModel
import com.example.qrholder.presentation.home.mapper.QrCodeToUiMapper
import com.example.qrholder.presentation.home.mapper.QrCodesMapper
import com.example.qrholder.presentation.home.ui.TestHomeCommunications
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

abstract class AbstractBuildQrCodeViewModelTest {

    protected lateinit var dispatchersList: DispatchersList
    protected lateinit var manageResources: ManageResources
    protected lateinit var communications: BuildQrCodeCommunications
    protected lateinit var viewModel: BuildQrCodeViewModel
    protected lateinit var title : String
    protected lateinit var content : String

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)

        communications = TestBuildQrCodeCommunications()
        dispatchersList = TestDispatchersList()
        manageResources = TestManageResources()
        title = ""
        content = ""

        viewModel = BuildQrCodeViewModel(
            dispatchers = dispatchersList,
            communications = communications,
            manageResources = manageResources,
            titleText = title,
            contentText = content,
//            TitleTextMapper.Base(manageResources,communications.titleCommunication),
//            ContentTextMapper.Base(manageResources,communications.titleCommunication)
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }
}