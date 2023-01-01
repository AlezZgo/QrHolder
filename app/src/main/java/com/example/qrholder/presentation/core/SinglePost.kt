package com.example.qrholder.presentation.core

import com.example.qrholder.presentation.core.viewmodel.Communication

abstract class SinglePost<T : Any> : Communication.Post<T>(SingleLiveEvent())