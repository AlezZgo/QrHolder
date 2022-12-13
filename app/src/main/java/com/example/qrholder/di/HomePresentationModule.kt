package com.example.qrholder.di

import com.example.qrholder.home.domain.HomeInteractor
import com.example.qrholder.home.domain.QrCode
import com.example.qrholder.home.domain.QrCodes
import com.example.qrholder.home.ui.*
import com.example.qrholder.home.ui.mapper.CompleteListMapper
import com.example.qrholder.home.ui.mapper.QrCodeToUiMapper
import com.example.qrholder.home.ui.mapper.QrCodesMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
interface HomePresentationModule {

    @Binds
    fun bindHomeCommunications(communications : HomeCommunications.Base) : HomeCommunications

    @Binds
    fun bindFilterCommunication(communications : FilterCommunication.Base) : FilterCommunication

    @Binds
    fun bindUiStateCommunication(communications : HomeUiStateCommunication.Base) : HomeUiStateCommunication

    @Binds
    fun bindCompleteListCommunication(communications : CompleteListCommunication.Base) : CompleteListCommunication

    @Binds
    fun bindQrCodesMapper(mapper : QrCodesMapper) : QrCodes.Mapper<Unit>

    @Binds
    fun bindCompleteListMapper(mapper : CompleteListMapper) : QrCodeUiCompleteList.Mapper<Unit>

    @Binds
    fun bindQrCodeToUiMapper(mapper : QrCodeToUiMapper) : QrCode.Mapper<QrCodeUi>

}