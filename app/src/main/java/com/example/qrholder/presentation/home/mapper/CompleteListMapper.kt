package com.example.qrholder.presentation.home.mapper

import com.example.qrholder.presentation.core.viewmodel.Communication
import com.example.qrholder.presentation.home.model.HomeUi
import com.example.qrholder.presentation.core.model.QrCodeUi
import com.example.qrholder.presentation.home.model.QrCodeCompleteListUi
import javax.inject.Inject

class CompleteListMapper @Inject constructor() : QrCodeCompleteListUi.Mapper<Unit> {
    override fun map(
        completeList: List<QrCodeUi>,
        errorMessage: String,
        filter: String,
        uiState: Communication.Mutate<HomeUi>
    ) {
        val filtered = completeList.filter { it.contains(filter) }

        uiState.map(
            if (errorMessage.isNotEmpty())
                HomeUi.Error(errorMessage)
            else if (completeList.isEmpty())
                HomeUi.Empty
            else if (filtered.isEmpty())
                HomeUi.NothingWasFound
            else
                HomeUi.Success(filtered)
        )
    }
}
