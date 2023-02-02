package com.example.qrholder.presentation.home

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.qrholder.presentation.core.ObserveUiState
import com.example.qrholder.presentation.core.ShowState
import com.example.qrholder.presentation.core.viewmodel.Communication
import com.example.qrholder.presentation.home.model.HomeUi
import com.example.qrholder.presentation.home.model.QrCodeCompleteListUi
import javax.inject.Inject

interface HomeCommunications : ObserveHomeUiState, ShowState<HomeUi>,Refilter {

    fun changeCompleteList(qrCodes: QrCodeCompleteListUi)

    fun filter(text: String)

    class Base @Inject constructor(
        private val uiStateCommunication: HomeUiStateCommunication,
        private val filterCommunication: FilterCommunication,
        private val qrCodesCompleteListCommunication: CompleteListCommunication
    ) : HomeCommunications {
        override fun showState(state: HomeUi) = uiStateCommunication.map(state)

        override fun changeCompleteList(qrCodes: QrCodeCompleteListUi) =
            qrCodesCompleteListCommunication.map(qrCodes)

        override fun filter(text: String) {
            filterCommunication.map(text)
            qrCodesCompleteListCommunication.filter(text, uiStateCommunication)
        }

        override fun reFilter() {
            qrCodesCompleteListCommunication.filter(filterCommunication.currentFilter(),uiStateCommunication)
        }

        override fun observeUiState(owner: LifecycleOwner, observer: Observer<HomeUi>) {
            uiStateCommunication.observe(owner, observer)
        }
    }

}

interface ObserveHomeUiState : ObserveUiState<HomeUi>

interface HomeUiStateCommunication : Communication.Mutable<HomeUi> {
    class Base @Inject constructor() : Communication.Post<HomeUi>(), HomeUiStateCommunication
}

interface FilterCommunication : Communication.Mutable<String> {
    fun currentFilter() : String

    class Base @Inject constructor() : Communication.Post<String>(), FilterCommunication{

        override fun currentFilter() : String = liveData.value?:""

    }
}

interface CompleteListCommunication : Communication.Mutable<QrCodeCompleteListUi>,
    FilterToState<String, HomeUi> {

    class Base @Inject constructor(
        private val completeListMapper: QrCodeCompleteListUi.Mapper<Unit>
    ) : Communication.Post<QrCodeCompleteListUi>(),
        CompleteListCommunication {

        override fun filter(filter: String, uiState: Communication.Mutable<HomeUi>) {
            liveData.value?.map(completeListMapper, filter, uiState)
        }


    }
}

interface FilterToState<F : Any, S : Any> {

    fun filter(filter: F, uiState: Communication.Mutable<HomeUi>)
}

interface Refilter {
    fun  reFilter()
}

