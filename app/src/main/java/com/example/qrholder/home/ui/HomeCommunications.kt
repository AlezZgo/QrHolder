package com.example.qrholder.home.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.qrholder.core.Communication

interface HomeCommunications : ObserveQrCodes {

    fun showState(state: HomeUiState)

    fun showList(list : List<QrCodeUi>)

    fun filter(text: String)

    class Base(
        private val uiState: HomeUiStateCommunication,
        private val filter: FilterCommunication,
        private val qrCodesCompleteList: QrCodesCommunication
    ) : HomeCommunications{
        override fun showState(state: HomeUiState) = uiState.map(state)

        override fun showList(list: List<QrCodeUi>) = qrCodesCompleteList.map(list)

        override fun filter(text: String) = filter.map(text)

        override fun observeUiState(owner: LifecycleOwner, observer: Observer<HomeUiState>) {
            uiState.observe(owner,observer)
        }

        override fun observeFilter(owner: LifecycleOwner, observer: Observer<String>) {
            filter.observe(owner,observer)
        }

        override fun observeQrCodes(owner: LifecycleOwner, observer: Observer<List<QrCodeUi>>) {
            qrCodesCompleteList.observe(owner,observer)
        }

    }

}

interface ObserveQrCodes{

    fun observeUiState(owner: LifecycleOwner,observer : Observer<HomeUiState>)
    fun observeFilter(owner: LifecycleOwner,observer : Observer<String>)
    fun observeQrCodes(owner: LifecycleOwner,observer : Observer<List<QrCodeUi>>)
}

interface HomeUiStateCommunication : Communication.Mutable<HomeUiState>{
    class Base() : Communication.Post<HomeUiState>(), HomeUiStateCommunication
}

interface FilterCommunication : Communication.Mutable<String>{
    class Base() : Communication.Post<String>(MutableLiveData("")), FilterCommunication
}

interface QrCodesCommunication : Communication.Mutable<List<QrCodeUi>>{
    class Base() : Communication.Post<List<QrCodeUi>>(MutableLiveData(emptyList())), QrCodesCommunication
}
