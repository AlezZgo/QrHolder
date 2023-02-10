package com.alezzgo.qrholder.presentation.core

import com.alezzgo.qrholder.presentation.core.viewmodel.Communication

abstract class SinglePost<T : Any> : Communication.Post<T>(SingleLiveEvent())