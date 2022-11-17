package com.example.qrholder.home.ui

import com.example.qrholder.core.Communication
import com.example.qrholder.home.domain.QrCode

class CompleteListMapper : QrCodeUiCompleteList.Mapper<Unit> {
    override fun map(
        completeList: List<QrCodeUi>,
        errorMessage: String,
        filter: String,
        uiState: Communication.Mutable<HomeUiState>
    ) {
        val filtered = completeList.filter { it.contains(filter) }

        uiState.map(
            if(errorMessage.isNotEmpty())
                HomeUiState.Error(errorMessage)
            else if (completeList.isEmpty())
                HomeUiState.Empty
            else if(filtered.isEmpty())
                HomeUiState.NothingWasFound
            else
                HomeUiState.Success(filtered)
        )
    }
}
