package com.example.qrholder.home.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.qrholder.core.Communication

interface HomeCommunications : ObserveQrCodes {

    fun showState(state: HomeUiState)

    fun changeCompleteList(list: List<QrCodeUi>)

    fun filter(text: String)

    class Base(
        private val uiState: HomeUiStateCommunication,
        private val filter: FilterCommunication,
        private val qrCodesCompleteList: CompleteListCommunication
    ) : HomeCommunications {
        override fun showState(state: HomeUiState) = uiState.map(state)

        override fun changeCompleteList(list: List<QrCodeUi>) = qrCodesCompleteList.map(list)

        override fun filter(text: String) {
            filter.map(text)
            qrCodesCompleteList.filter(text,uiState)
        }

        override fun observeUiState(owner: LifecycleOwner, observer: Observer<HomeUiState>) {
            uiState.observe(owner, observer)
        }

        //todo reason?
        override fun observeFilter(owner: LifecycleOwner, observer: Observer<String>) {
            filter.observe(owner, observer)
        }

        //todo reason?
        override fun observeQrCodes(owner: LifecycleOwner, observer: Observer<List<QrCodeUi>>) {
            qrCodesCompleteList.observe(owner, observer)
        }

    }

}

interface ObserveQrCodes {

    fun observeUiState(owner: LifecycleOwner, observer: Observer<HomeUiState>)

    //todo reason?
    fun observeFilter(owner: LifecycleOwner, observer: Observer<String>)

    //todo reason?
    fun observeQrCodes(owner: LifecycleOwner, observer: Observer<List<QrCodeUi>>)
}

interface HomeUiStateCommunication : Communication.Mutable<HomeUiState> {
    class Base() : Communication.Post<HomeUiState>(), HomeUiStateCommunication
}

interface FilterCommunication : Communication.Mutable<String> {
    class Base() : Communication.Post<String>(MutableLiveData("")), FilterCommunication
}

interface CompleteListCommunication : Communication.Mutable<List<QrCodeUi>>,
    FilterToState<String, HomeUiState> {

    class Base() : Communication.Post<List<QrCodeUi>>(MutableLiveData(emptyList())),
        CompleteListCommunication {
        override fun filter(filter: String, uiState: Communication.Mutable<HomeUiState>) {
            val filtered: List<QrCodeUi> =
                liveData.value?.filter { it.contains(filter) } ?: emptyList()
            if (filter.isEmpty())
                uiState.map(HomeUiState.NothingWasFound)
            else
                uiState.map(HomeUiState.Success(filtered))
        }
    }
}

interface FilterToState<F : Any, S : Any> {

    fun filter(filter: F, uiState: Communication.Mutable<HomeUiState>)
}
