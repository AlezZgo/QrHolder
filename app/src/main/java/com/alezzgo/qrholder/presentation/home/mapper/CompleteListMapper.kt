package com.alezzgo.qrholder.presentation.home.mapper

import com.alezzgo.qrholder.presentation.core.model.QrCodeUi
import com.alezzgo.qrholder.presentation.core.viewmodel.Communication
import com.alezzgo.qrholder.presentation.home.model.HomeUi
import com.alezzgo.qrholder.presentation.home.model.QrCodeCompleteListUi
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
