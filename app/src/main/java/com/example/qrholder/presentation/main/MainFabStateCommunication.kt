package com.example.qrholder.presentation.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.qrholder.presentation.core.viewmodel.Communication
import javax.inject.Inject

interface MainFabStateCommunication : Communication.Mutable<MainFabUiState> {

    class Base @Inject constructor() : Communication.Post<MainFabUiState>(),MainFabStateCommunication

}


