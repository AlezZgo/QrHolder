package com.example.qrholder.home

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.qrholder.home.domain.HomeInteractor
import com.example.qrholder.home.domain.QrCodes
import com.example.qrholder.home.ui.*

abstract class BaseTest(

) {

    //dependencies
    val communications = TestHomeCommunications()
    val interactor = TestHomeInteractor()

    //initialize
    val viewModel = HomeViewModel(
        communications = communications,
        interactor = interactor,
        qrCodesResultMapper = QrCodesResultMapper(communications, QrCodeToUiMapper())
    )



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
        val filterCalledList = mutableListOf(EMPTY_FIELD)
        val fetchListCalledList = mutableListOf<List<QrCodeUi>>()

        override fun showState(state: HomeUiState) {
            uiStateCalledList.add(state)
        }

        override fun showList(list: List<QrCodeUi>) {
            fetchListCalledList.add(list)
        }

        override fun filter(text: String) {
            filterCalledList.add(text)
        }

        override fun observeUiState(owner: LifecycleOwner, observer: Observer<HomeUiState>) = Unit

        override fun observeFilter(owner: LifecycleOwner, observer: Observer<String>) = Unit

        override fun observeQrCodes(owner: LifecycleOwner, observer: Observer<List<QrCodeUi>>) = Unit

        companion object {
            const val EMPTY_FIELD = ""
        }

    }
}