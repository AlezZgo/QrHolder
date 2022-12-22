package com.example.qrholder.presentation.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface ObserveUiState<T> {

    fun observeUiState(owner: LifecycleOwner, observer: Observer<T>)
}