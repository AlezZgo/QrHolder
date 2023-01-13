package com.example.qrholder.presentation.buildQrCode

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.qrholder.data.QrCodesRepository
import com.example.qrholder.domain.model.QrCodeBuildResult
import com.example.qrholder.presentation.core.viewmodel.AbstractViewModel
import com.example.qrholder.presentation.core.viewmodel.DispatchersList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BuildQrCodeViewModel @Inject constructor(
    private val communication: BuildQrCodeResultCommunication,
    private val qrCodeInBuild : QrCodeInBuild,
    private val dispatchers: DispatchersList,
    ) : AbstractViewModel(), ChangeTitle, ChangeContent, Build,
     ObserveQrCodeBuildResult {

    override fun init() {}

    override fun observeBuildResultState(
        owner: LifecycleOwner,
        observer: Observer<QrCodeBuildResult>
    ) = communication.observe(owner,observer)

    override fun changeTitle(title: String) = qrCodeInBuild.changeTitle(title)

    override fun changeContent(content: String) = qrCodeInBuild.changeContent(content)

    override fun build() {
        viewModelScope.launch(dispatchers.io()) {
            communication.map(qrCodeInBuild.build())
        }
    }

}

interface ChangeTitle {
    fun changeTitle(title: String)
}

interface ChangeContent {
    fun changeContent(content: String)
}

interface Create<I,O> {
    fun create(input : I) : O
}

interface Build{
    fun build()
}

