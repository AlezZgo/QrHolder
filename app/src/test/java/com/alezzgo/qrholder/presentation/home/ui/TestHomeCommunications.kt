package com.alezzgo.qrholder.presentation.home.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.alezzgo.qrholder.presentation.home.HomeCommunications
import com.alezzgo.qrholder.presentation.home.mapper.CompleteListMapper
import com.alezzgo.qrholder.presentation.home.model.HomeUi
import com.alezzgo.qrholder.presentation.home.model.QrCodeCompleteListUi

class TestHomeCommunications : HomeCommunications {

    val testHomeCommunication = TestHomeUiStateCommunication()
    private val mapper = CompleteListMapper()

    val uiStateCalledList = testHomeCommunication.homeUiCalledList
    val filterCalledList = mutableListOf<String>()
    val qrCodesCompleteCalledList = mutableListOf<QrCodeCompleteListUi>()

    private var qrCodesCompleteList: QrCodeCompleteListUi =
        QrCodeCompleteListUi.Success(emptyList())

    override fun showState(state: HomeUi) {
        testHomeCommunication.map(state)
    }

    override fun reFilter() {
        filterCalledList.add(filterCalledList.last())
        qrCodesCompleteList.map(
            mapper = mapper,
            filter = filterCalledList.last(),
            uiState = testHomeCommunication
        )
    }

    override fun changeCompleteList(qrCodes: QrCodeCompleteListUi) {
        qrCodesCompleteCalledList.add(qrCodes)
        qrCodesCompleteList = qrCodes
    }

    override fun filter(text: String) {
        filterCalledList.add(text)
        qrCodesCompleteList.map(mapper = mapper, filter = text, uiState = testHomeCommunication)
    }

    override fun observeUiState(owner: LifecycleOwner, observer: Observer<HomeUi>) = Unit

}