package com.example.qrholder.home

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.qrholder.home.domain.HomeInteractor
import com.example.qrholder.home.domain.QrCodes
import com.example.qrholder.home.ui.DispatchersList
import com.example.qrholder.home.ui.HomeCommunications
import com.example.qrholder.home.ui.HomeUiState
import com.example.qrholder.home.ui.QrCodeUi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher

abstract class BaseTest {

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

        val uiStateCalledList = mutableListOf<HomeUiState>()
        val filterCalledList = mutableListOf<String>()
        val qrCodesCompleteCalledList = mutableListOf<List<QrCodeUi>>()

        private var qrCodesCompleteList = emptyList<QrCodeUi>()

        override fun showState(state: HomeUiState) {
            uiStateCalledList.add(state)
        }

        override fun changeCompleteList(list: List<QrCodeUi>) {
            qrCodesCompleteCalledList.add(list)
        }

        //todo move the code to a separate class (the same code in the HomeCommunications class)
        override fun filter(text: String) {
            filterCalledList.add(text)

            if(qrCodesCompleteList.isEmpty())
                uiStateCalledList.add(HomeUiState.Empty)
            else{
                val filtered =
                    qrCodesCompleteList.filter { it.contains(text) }
                if (filtered.isEmpty())
                    uiStateCalledList.add(HomeUiState.Empty)
                else
                    uiStateCalledList.add(HomeUiState.Success(filtered))
            }
        }

        override fun observeUiState(owner: LifecycleOwner, observer: Observer<HomeUiState>) = Unit

        override fun observeFilter(owner: LifecycleOwner, observer: Observer<String>) = Unit

        override fun observeQrCodes(owner: LifecycleOwner, observer: Observer<List<QrCodeUi>>) =
            Unit
    }
}