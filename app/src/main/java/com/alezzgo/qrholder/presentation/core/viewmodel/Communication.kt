package com.alezzgo.qrholder.presentation.core.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alezzgo.qrholder.core.Mapper

interface Communication {

    interface Observe<T> {
        fun observe(owner: LifecycleOwner, observer: Observer<T>)
    }

    interface Mutate<T> : Mapper.Unit<T>

    interface Mutable<T> : Observe<T>, Mutate<T>

    abstract class Abstract<T>(
        protected val liveData: MutableLiveData<T> = MutableLiveData()
    ) : Mutable<T> {

        override fun observe(owner: LifecycleOwner, observer: Observer<T>) =
            liveData.observe(owner, observer)
    }

    abstract class Ui<T : Any>(
        liveData: MutableLiveData<T> = MutableLiveData()
    ) : Abstract<T>(liveData) {

        override fun map(source: T) {
            liveData.value = source
        }
    }

    abstract class Post<T : Any>(
        postLiveData: MutableLiveData<T> = MutableLiveData()
    ) : Abstract<T>(postLiveData) {
        override fun map(source: T) = liveData.postValue(source)

    }


}