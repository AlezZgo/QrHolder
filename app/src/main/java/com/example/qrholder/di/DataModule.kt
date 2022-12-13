package com.example.qrholder.di

import com.example.qrholder.home.data.QrCodeData
import com.example.qrholder.home.data.QrCodeDataToDomainMapper
import com.example.qrholder.home.data.QrCodesRepository
import com.example.qrholder.home.data.cache.QrCodesCacheDataSource
import com.example.qrholder.home.domain.QrCode
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface DataModule {

    @Binds
    fun bindQrCodesRepository( qrCodesRepository: QrCodesRepository.Base) : QrCodesRepository

    @Binds
    fun bindQrCodesCacheDataSource( qrCodesCacheDataSource: QrCodesCacheDataSource.Base) : QrCodesCacheDataSource

    @Binds
    fun bindQrCodeDataToDomainMapper(mapper : QrCodeDataToDomainMapper) : QrCodeData.Mapper<QrCode>
}