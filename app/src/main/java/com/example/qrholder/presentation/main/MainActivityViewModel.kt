package com.example.qrholder.presentation.main

import androidx.lifecycle.viewModelScope
import com.example.qrholder.presentation.core.viewmodel.AbstractViewModel
import com.example.qrholder.presentation.core.viewmodel.DispatchersList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val fabState : MainFabStateCommunication,
    private val dispatchers: DispatchersList
) : AbstractViewModel(), ChangeFabState<MainFabUiState> {
    override fun init() {
        viewModelScope.launch(dispatchers.io()) {
            fabState.map(MainFabUiState.Closed)
        }
    }

    override fun changeFabState(model: MainFabUiState) {
        fabState.map(model)
    }

}

interface ChangeFabState<T>{

    fun changeFabState(model : T)
}