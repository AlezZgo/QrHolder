package com.example.qrholder.presentation.core

interface InitUI {
    fun setupViews() = Unit

    fun setupListeners() = Unit

    fun observe() = Unit
}