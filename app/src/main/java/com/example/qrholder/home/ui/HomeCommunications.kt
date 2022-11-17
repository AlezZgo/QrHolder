package com.example.qrholder.home.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.qrholder.core.Communication

interface HomeCommunications : ObserveQrCodes {

    fun showState(state: HomeUiState)

    fun changeCompleteList(qrCodes: QrCodeUiCompleteList)

    fun filter(text: String)

    class Base(
        private val uiState: HomeUiStateCommunication,
        private val filter: FilterCommunication,
        private val qrCodesCompleteList: CompleteListCommunication
    ) : HomeCommunications {
        override fun showState(state: HomeUiState) = uiState.map(state)

        override fun changeCompleteList(qrCodes: QrCodeUiCompleteList) = qrCodesCompleteList.map(qrCodes)

        override fun filter(text: String) {
            filter.map(text)
            qrCodesCompleteList.filter(text, uiState)
        }

        override fun observeUiState(owner: LifecycleOwner, observer: Observer<HomeUiState>) {
            uiState.observe(owner, observer)
        }

        //todo valuable reason?
        override fun observeFilter(owner: LifecycleOwner, observer: Observer<String>) {
            filter.observe(owner, observer)
        }
    }

}

interface ObserveQrCodes {

    fun observeUiState(owner: LifecycleOwner, observer: Observer<HomeUiState>)

    //todo valuable reason?
    fun observeFilter(owner: LifecycleOwner, observer: Observer<String>)
}

interface HomeUiStateCommunication : Communication.Mutable<HomeUiState> {
    class Base : Communication.Post<HomeUiState>(), HomeUiStateCommunication
}

interface FilterCommunication : Communication.Mutable<String> {
    class Base : Communication.Post<String>(), FilterCommunication
}

interface CompleteListCommunication : Communication.Mutable<QrCodeUiCompleteList>,
    FilterToState<String, HomeUiState> {

    class Base(
        private val completeListMapper: QrCodeUiCompleteList.Mapper<Unit>
    ) : Communication.Post<QrCodeUiCompleteList>(),
        CompleteListCommunication {

        //todo Is the naming correct here or not?
        override fun filter(filter: String, uiState: Communication.Mutable<HomeUiState>){
            liveData.value?.map(completeListMapper,filter,uiState)
        }

    }
}

interface FilterToState<F : Any, S : Any> {

    fun filter(filter: F, uiState: Communication.Mutable<HomeUiState>)
}

