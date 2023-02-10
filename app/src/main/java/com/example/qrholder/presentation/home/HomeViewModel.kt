package com.example.qrholder.presentation.home

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.qrholder.R
import com.example.qrholder.core.ManageResources
import com.example.qrholder.domain.FetchQrCode
import com.example.qrholder.domain.HomeInteractor
import com.example.qrholder.domain.model.QrCode
import com.example.qrholder.domain.model.QrCodes
import com.example.qrholder.presentation.core.ObserveUiState
import com.example.qrholder.presentation.core.model.QrCodeUi
import com.example.qrholder.presentation.core.viewmodel.AbstractViewModel
import com.example.qrholder.presentation.core.viewmodel.DispatchersList
import com.example.qrholder.presentation.home.mapper.QrCodeToUiMapper
import com.example.qrholder.presentation.home.model.HomeUi
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
) : AbstractViewModel(), ObserveUiState<HomeUi>, Filter<String>,Delete<QrCodeUi>,
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

            //todo switch dispatcher isn`t right there? is it?
            //todo it works just because it takes some delay,
            //todo during which complete list would finish initializing
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

interface Delete<T>{
    fun delete(model: T)
}