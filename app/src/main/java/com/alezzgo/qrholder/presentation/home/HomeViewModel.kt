package com.alezzgo.qrholder.presentation.home

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.alezzgo.qrholder.R
import com.alezzgo.qrholder.core.ManageResources
import com.alezzgo.qrholder.domain.FetchQrCode
import com.alezzgo.qrholder.domain.HomeInteractor
import com.alezzgo.qrholder.domain.model.QrCode
import com.alezzgo.qrholder.domain.model.QrCodes
import com.alezzgo.qrholder.presentation.core.ObserveUiState
import com.alezzgo.qrholder.presentation.core.model.QrCodeUi
import com.alezzgo.qrholder.presentation.core.viewmodel.AbstractViewModel
import com.alezzgo.qrholder.presentation.core.viewmodel.DispatchersList
import com.alezzgo.qrholder.presentation.home.model.HomeUi
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
    private val qrCodeToUiMapper: QrCode.Mapper<QrCodeUi>
) : AbstractViewModel(), ObserveUiState<HomeUi>, Filter<String>, Delete<QrCodeUi>,
    FetchQrCode<QrCodeUi> {

    override fun observeUiState(owner: LifecycleOwner, observer: Observer<HomeUi>) =
        communications.observeUiState(owner, observer)

    override fun filter(filter: String) = communications.filter(filter)

    override fun init() {
        viewModelScope.launch(dispatchers.io()) {
//            use it with internet loading
//            communications.showState(HomeUi.Loading)
            val result = interactor.fetchAll()
            result.map(fetchAllResultMapper)
            withContext(dispatchers.ui()) {
                filter(manageResources.string(R.string.empty))
            }
        }
    }

    override fun delete(model: QrCodeUi) {
        viewModelScope.launch(dispatchers.io()) {
            model.delete(delete = interactor)
            interactor.fetchAll().map(fetchAllResultMapper)
            withContext(dispatchers.ui()) {
                communications.reFilter()
            }
        }
    }

    override suspend fun fetchQrCode(title: String): QrCodeUi {
        return interactor.fetchQrCode(title = title).map(qrCodeToUiMapper)
    }
}


interface Filter<T> {
    fun filter(filter: T)
}

interface Delete<T> {
    fun delete(model: T)
}