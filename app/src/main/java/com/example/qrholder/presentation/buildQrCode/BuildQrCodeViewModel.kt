package com.example.qrholder.presentation.buildQrCode

import com.example.qrholder.core.ManageResources
import com.example.qrholder.presentation.core.viewmodel.AbstractViewModel
import com.example.qrholder.presentation.core.viewmodel.DispatchersList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BuildQrCodeViewModel @Inject constructor(
    private val dispatchers : DispatchersList,
    private val communications : BuildQrCodeCommunications,
    private val manageResources : ManageResources
) : AbstractViewModel(),ChangeTitle,ChangeContent {

    override fun init() {}

    override fun changeTitle(title: String) {
        TODO("Not yet implemented")
    }

    override fun changeContent(content: String) {
        TODO("Not yet implemented")
    }


}

interface ChangeTitle{
    fun changeTitle(title: String)
}

interface ChangeContent{
    fun changeContent(content: String)
}

