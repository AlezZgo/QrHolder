package com.example.qrholder.di

import com.example.qrholder.data.QrCodeData
import com.example.qrholder.data.mapper.QrCodeDataToDomainMapper
import com.example.qrholder.data.QrCodesRepository
import com.example.qrholder.data.cache.QrCodesCacheDataSource
import com.example.qrholder.data.cache.db.QrCodeCache
import com.example.qrholder.data.mapper.QrCodeDataToCacheMapper
import com.example.qrholder.data.mapper.QrCodeToDataMapper
import com.example.qrholder.domain.model.QrCode
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
    fun bindQrCodesCacheDataSource(qrCodesCacheDataSource: QrCodesCacheDataSource.Base): QrCodesCacheDataSource

    @Binds
    fun bindQrCodeDataToDomainMapper(mapper: QrCodeDataToDomainMapper): QrCodeData.Mapper<QrCode>

    @Binds
    fun bindQrCodeDataToCacheMapper(mapper: QrCodeDataToCacheMapper): QrCodeData.Mapper<QrCodeCache>

    @Binds
    fun bindQrCodeToDataMapper(mapper: QrCodeToDataMapper): QrCode.Mapper<QrCodeData>
}