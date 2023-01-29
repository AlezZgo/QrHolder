package com.example.qrholder.presentation.qrCodeImageDialog

import androidx.lifecycle.ViewModel
import com.example.qrholder.data.QrCodesRepository
import com.example.qrholder.data.SystemSettingsNeverShow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QrCodeImageDialogViewModel @Inject constructor(
    private val repository: QrCodesRepository
) : ViewModel(), SystemSettingsNeverShow {

    override fun fetchSystemSettingsNeverShow() =
        repository.fetchSystemSettingsNeverShow()


    override fun saveSystemSettingsNeverShow(neverShow: Boolean) =
        repository.saveSystemSettingsNeverShow(neverShow = neverShow)


}