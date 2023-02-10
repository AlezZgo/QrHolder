package com.alezzgo.qrholder.di

import com.alezzgo.qrholder.data.DeleteInternalStorage
import com.alezzgo.qrholder.data.QrCodeData
import com.alezzgo.qrholder.data.QrCodesRepository
import com.alezzgo.qrholder.data.cache.QrCodesCacheDataSource
import com.alezzgo.qrholder.data.cache.db.QrCodeCache
import com.alezzgo.qrholder.data.mapper.QrCodeDataToCacheMapper
import com.alezzgo.qrholder.data.mapper.QrCodeDataToDomainMapper
import com.alezzgo.qrholder.data.mapper.QrCodeToDataMapper
import com.alezzgo.qrholder.domain.model.QrCode
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface DataModule {

    @Binds
    fun bindQrCodesRepository(qrCodesRepository: QrCodesRepository.Base): QrCodesRepository

    @Binds
    fun bindDeleteInternalStorage(deleteInternalStorage: DeleteInternalStorage.Base): DeleteInternalStorage

    @Binds
    fun bindQrCodesCacheDataSource(qrCodesCacheDataSource: QrCodesCacheDataSource.Base): QrCodesCacheDataSource

    @Binds
    fun bindQrCodeDataToDomainMapper(mapper: QrCodeDataToDomainMapper): QrCodeData.Mapper<QrCode>

    @Binds
    fun bindQrCodeDataToCacheMapper(mapper: QrCodeDataToCacheMapper): QrCodeData.Mapper<QrCodeCache>

    @Binds
    fun bindQrCodeToDataMapper(mapper: QrCodeToDataMapper): QrCode.Mapper<QrCodeData>


}