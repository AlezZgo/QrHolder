package com.alezzgo.qrholder.presentation.core.viewmodel

import androidx.lifecycle.ViewModel

abstract class AbstractViewModel : ViewModel(), Init {

    override fun init(isFirstRun: Boolean) {
        if (isFirstRun)
            init()
        else
            initSecondTime()
    }

    protected abstract fun init()

    protected open fun initSecondTime() = Unit

}