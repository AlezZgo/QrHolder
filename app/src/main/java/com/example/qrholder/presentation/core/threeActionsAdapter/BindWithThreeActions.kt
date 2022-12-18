package com.example.qrholder.presentation.core.threeActionsAdapter

interface BindWithThreeActions<T> {

    fun bind(
        model: T,
        actionOne: (model: T) -> Unit,
        actionTwo: (model: T) -> Unit,
        actionThree: (model: T) -> Unit
    )
}