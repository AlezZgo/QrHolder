package com.alezzgo.qrholder.presentation.core.adapter.threeActionsAdapter

interface BindWithThreeActions<T> {

    fun bind(
        model: T,
        actionOne: (model: T) -> Unit,
        actionTwo: (model: T) -> Unit,
        actionThree: (model: T) -> Unit
    )
}