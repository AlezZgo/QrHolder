package com.example.qrholder.di

import com.example.qrholder.domain.QrCode
import com.example.qrholder.domain.QrCodes
import com.example.qrholder.presentation.home.*
import com.example.qrholder.presentation.home.mapper.CompleteListMapper
import com.example.qrholder.presentation.home.mapper.QrCodeToUiMapper
import com.example.qrholder.presentation.home.mapper.QrCodesMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
interface HomePresentationModule {

    @Singleton
    @Binds
    fun bindHomeCommunications(communications: HomeCommunications.Base): HomeCommunications

    @Singleton
    @Binds
    fun bindFilterCommunication(communications: FilterCommunication.Base): FilterCommunication

    @Singleton
    @Binds
    fun bindUiStateCommunication(communications: HomeUiStateCommunication.Base): HomeUiStateCommunication

    @Singleton
    @Binds
    fun bindCompleteListCommunication(communications: CompleteListCommunication.Base): CompleteListCommunication

    @Binds
    fun bindQrCodesMapper(mapper: QrCodesMapper): QrCodes.Mapper<Unit>

    @Binds
    fun bindCompleteListMapper(mapper: CompleteListMapper): QrCodeUiCompleteList.Mapper<Unit>

    @Binds
    fun bindQrCodeToUiMapper(mapper: QrCodeToUiMapper): QrCode.Mapper<QrCodeUi>

}






























