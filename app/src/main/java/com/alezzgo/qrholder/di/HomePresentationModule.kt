package com.alezzgo.qrholder.di

import com.alezzgo.qrholder.domain.model.QrCode
import com.alezzgo.qrholder.domain.model.QrCodes
import com.alezzgo.qrholder.presentation.core.model.QrCodeUi
import com.alezzgo.qrholder.presentation.home.*
import com.alezzgo.qrholder.presentation.home.mapper.CompleteListMapper
import com.alezzgo.qrholder.presentation.home.mapper.QrCodeToUiMapper
import com.alezzgo.qrholder.presentation.home.mapper.QrCodesMapper
import com.alezzgo.qrholder.presentation.home.model.QrCodeCompleteListUi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
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
    fun bindCompleteListMapper(mapper: CompleteListMapper): QrCodeCompleteListUi.Mapper<Unit>

    @Binds
    fun bindQrCodeToUiMapper(mapper: QrCodeToUiMapper): QrCode.Mapper<QrCodeUi>

}






























