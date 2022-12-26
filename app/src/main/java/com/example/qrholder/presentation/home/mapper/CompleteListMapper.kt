package com.example.qrholder.presentation.home.mapper

import com.example.qrholder.presentation.core.viewmodel.Communication
import com.example.qrholder.presentation.home.model.HomeUiState
import com.example.qrholder.presentation.home.model.QrCodeUi
import com.example.qrholder.presentation.home.model.QrCodeUiCompleteList
import javax.inject.Inject

class CompleteListMapper @Inject constructor() : QrCodeUiCompleteList.Mapper<Unit> {
    override fun map(
        completeList: List<QrCodeUi>,
        errorMessage: String,
        filter: String,
        uiState: Communication.Mutate<HomeUiState>
    ) {
        val filtered = completeList.filter { it.contains(filter) }

        uiState.map(
            if (errorMessage.isNotEmpty())
                HomeUiState.Error(errorMessage)
            else if (completeList.isEmpty())
                HomeUiState.Empty
            else if (filtered.isEmpty())
                HomeUiState.NothingWasFound
            else
                HomeUiState.Success(filtered)
        )
    }
}
