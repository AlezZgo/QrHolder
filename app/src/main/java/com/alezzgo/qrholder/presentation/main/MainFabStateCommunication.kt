package com.alezzgo.qrholder.presentation.main

import com.alezzgo.qrholder.presentation.core.viewmodel.Communication
import javax.inject.Inject

interface MainFabStateCommunication : Communication.Mutable<MainFabUiState> {

    class Base @Inject constructor() : Communication.Post<MainFabUiState>(),
        MainFabStateCommunication

}


