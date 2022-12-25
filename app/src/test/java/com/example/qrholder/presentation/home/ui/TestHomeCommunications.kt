package com.example.qrholder.presentation.home.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.qrholder.presentation.home.HomeCommunications
import com.example.qrholder.presentation.home.mapper.CompleteListMapper
import com.example.qrholder.presentation.home.model.HomeUiState
import com.example.qrholder.presentation.home.model.QrCodeUiCompleteList

class TestHomeCommunications : HomeCommunications {

    val testHomeCommunication = TestHomeUiStateCommunication()
    private val mapper = CompleteListMapper()

    val uiStateCalledList = testHomeCommunication.homeUiStateCalledList
    val filterCalledList = mutableListOf<String>()
    val qrCodesCompleteCalledList = mutableListOf<QrCodeUiCompleteList>()

    private var qrCodesCompleteList : QrCodeUiCompleteList = QrCodeUiCompleteList.Success(emptyList())

    override fun showState(state: HomeUiState) {
        testHomeCommunication.map(state)
    }

    override fun changeCompleteList(qrCodes: QrCodeUiCompleteList) {
        qrCodesCompleteCalledList.add(qrCodes)
        qrCodesCompleteList = qrCodes
    }

    override fun filter(text: String) {
        filterCalledList.add(text)
        qrCodesCompleteList.map(mapper = mapper, filter = text, uiState = testHomeCommunication)
    }

    override fun observeUiState(owner: LifecycleOwner, observer: Observer<HomeUiState>) = Unit

}