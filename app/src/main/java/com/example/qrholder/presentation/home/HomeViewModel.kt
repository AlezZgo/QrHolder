package com.example.qrholder.presentation.home

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.qrholder.R
import com.example.qrholder.core.ManageResources
import com.example.qrholder.core.ui.AbstractViewModel
import com.example.qrholder.core.ui.DispatchersList
import com.example.qrholder.data.cache.db.QrCodesDao
import com.example.qrholder.domain.HomeInteractor
import com.example.qrholder.domain.QrCodes
import com.example.qrholder.presentation.home.model.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dispatchers: DispatchersList,
    private val interactor: HomeInteractor,
    private val communications: HomeCommunications,
    private val manageResources: ManageResources,
    private val fetchAllResultMapper: QrCodes.Mapper<Unit>,
    private val dao: QrCodesDao
) : AbstractViewModel(), ObserveQrCodes, Filter<String> {

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
            withContext(dispatchers.ui()) {
                filter(manageResources.string(R.string.empty))
            }
        }
    }
}

interface Filter<T> {
    fun filter(filter: T)
}