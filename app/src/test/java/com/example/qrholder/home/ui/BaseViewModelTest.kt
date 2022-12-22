package com.example.qrholder.home.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.qrholder.core.ui.DispatchersList
import com.example.qrholder.domain.HomeInteractor
import com.example.qrholder.domain.QrCodes
import com.example.qrholder.presentation.home.mapper.CompleteListMapper
import com.example.qrholder.presentation.home.HomeCommunications
import com.example.qrholder.presentation.home.model.HomeUiState
import com.example.qrholder.presentation.home.HomeUiStateCommunication
import com.example.qrholder.presentation.home.model.QrCodeUiCompleteList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher

abstract class BaseViewModelTest {

    class TestDispatchersList : DispatchersList {
        override fun io(): CoroutineDispatcher = TestCoroutineDispatcher()

        override fun ui(): CoroutineDispatcher = TestCoroutineDispatcher()

    }

    class TestHomeInteractor : HomeInteractor {

        private var result: QrCodes = QrCodes.Success(emptyList())

        val fetchAllCalledList = mutableListOf<QrCodes>()

        fun changeExpectedResult(qrCodes: QrCodes) {
            result = qrCodes
        }

        override suspend fun fetchAll(): QrCodes {
            fetchAllCalledList.add(result)
            return result
        }
    }

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

        override fun observeFilter(owner: LifecycleOwner, observer: Observer<String>) = Unit

    }

    class TestHomeUiStateCommunication : HomeUiStateCommunication {

        var homeUiStateCalledList = mutableListOf<HomeUiState>()

        override fun observe(owner: LifecycleOwner, observer: Observer<HomeUiState>) = Unit

        override fun map(source: HomeUiState) {
            homeUiStateCalledList.add(source)
        }

    }
}