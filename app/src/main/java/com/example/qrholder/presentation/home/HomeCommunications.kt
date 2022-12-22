package com.example.qrholder.presentation.home

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.qrholder.presentation.core.ObserveUiState
import com.example.qrholder.presentation.core.ShowState
import com.example.qrholder.presentation.core.viewmodel.Communication
import com.example.qrholder.presentation.home.model.HomeUiState
import com.example.qrholder.presentation.home.model.QrCodeUiCompleteList
import javax.inject.Inject

interface HomeCommunications : ObserveHomeUiState, ShowState<HomeUiState> {

    fun changeCompleteList(qrCodes: QrCodeUiCompleteList)

    fun filter(text: String)

    class Base @Inject constructor(
        private val uiState: HomeUiStateCommunication,
        private val filter: FilterCommunication,
        private val qrCodesCompleteList: CompleteListCommunication
    ) : HomeCommunications {
        override fun showState(state: HomeUiState) = uiState.map(state)

        override fun changeCompleteList(qrCodes: QrCodeUiCompleteList) =
            qrCodesCompleteList.map(qrCodes)

        override fun filter(text: String) {
            filter.map(text)
            qrCodesCompleteList.filter(text, uiState)
        }

        override fun observeUiState(owner: LifecycleOwner, observer: Observer<HomeUiState>) {
            uiState.observe(owner, observer)
        }
    }

}

interface ObserveHomeUiState : ObserveUiState<HomeUiState>

interface HomeUiStateCommunication : Communication.Mutable<HomeUiState> {
    class Base @Inject constructor() : Communication.Post<HomeUiState>(), HomeUiStateCommunication
}

interface FilterCommunication : Communication.Mutable<String> {
    class Base @Inject constructor() : Communication.Post<String>(), FilterCommunication
}

interface CompleteListCommunication : Communication.Mutable<QrCodeUiCompleteList>,
    FilterToState<String, HomeUiState> {

    class Base @Inject constructor(
        private val completeListMapper: QrCodeUiCompleteList.Mapper<Unit>
    ) : Communication.Post<QrCodeUiCompleteList>(),
        CompleteListCommunication {

        override fun filter(filter: String, uiState: Communication.Mutable<HomeUiState>) {
            liveData.value?.map(completeListMapper, filter, uiState)
        }
    }
}

interface FilterToState<F : Any, S : Any> {

    fun filter(filter: F, uiState: Communication.Mutable<HomeUiState>)
}

