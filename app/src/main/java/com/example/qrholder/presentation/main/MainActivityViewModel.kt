package com.example.qrholder.presentation.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.qrholder.presentation.core.ObserveUiState
import com.example.qrholder.presentation.core.viewmodel.AbstractViewModel
import com.example.qrholder.presentation.core.viewmodel.DispatchersList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val fabState: MainFabStateCommunication,
    private val dispatchers: DispatchersList
) : AbstractViewModel(), ChangeFabState<MainFabUiState>, ObserveUiState<MainFabUiState> {

    override fun init() {
        viewModelScope.launch(dispatchers.io()) {
            fabState.map(MainFabUiState.Closed)
        }
    }

    override fun changeFabState(model: MainFabUiState) = fabState.map(model)


    override fun observeUiState(owner: LifecycleOwner, observer: Observer<MainFabUiState>) =
        fabState.observe(owner, observer)

}

interface ChangeFabState<T> {

    fun changeFabState(model: T)
}