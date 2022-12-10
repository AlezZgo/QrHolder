package com.example.qrholder.home.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.qrholder.core.ui.AbstractViewModel
import com.example.qrholder.core.ui.DispatchersList
import com.example.qrholder.home.domain.HomeInteractor
import com.example.qrholder.home.domain.QrCodes
import kotlinx.coroutines.launch

//todo use hilt to get deps
class HomeViewModel(
    private val dispatchers: DispatchersList,
    private val interactor: HomeInteractor,
    private val communications: HomeCommunications,
    private val defaultFilter: String = "",
    private val fetchAllResultMapper: QrCodes.Mapper<Unit>,
) : AbstractViewModel(), ObserveQrCodes, Filter<String>{

    override fun observeUiState(owner: LifecycleOwner, observer: Observer<HomeUiState>) =
        communications.observeUiState(owner, observer)

    override fun observeFilter(owner: LifecycleOwner, observer: Observer<String>) =
        communications.observeFilter(owner, observer)

    override fun filter(filter: String) = communications.filter(filter)

    override fun init() {
        viewModelScope.launch(dispatchers.io()) {
            communications.showState(HomeUiState.Loading)
            val result = interactor.fetchAll()
            result.map(fetchAllResultMapper)
            filter(defaultFilter)
        }
    }

}

interface Filter<T> {
    fun filter(filter: T)
}