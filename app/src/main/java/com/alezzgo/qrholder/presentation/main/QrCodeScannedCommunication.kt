package com.alezzgo.qrholder.presentation.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.alezzgo.qrholder.presentation.core.SinglePost
import com.alezzgo.qrholder.presentation.core.viewmodel.Communication
import javax.inject.Inject

interface QrCodeScannedCommunication : Communication.Mutable<String> {
    class Base @Inject constructor() : SinglePost<String>(), QrCodeScannedCommunication {
        override fun observe(owner: LifecycleOwner, observer: Observer<String>) = Unit
    }
}