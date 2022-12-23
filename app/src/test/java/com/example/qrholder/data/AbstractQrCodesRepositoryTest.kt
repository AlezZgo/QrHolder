package com.example.qrholder.data

import com.example.qrholder.core.TestManageResources
import org.junit.jupiter.api.BeforeEach

abstract class AbstractQrCodesRepositoryTest {

    protected lateinit var repository: QrCodesRepository
    protected lateinit var mapper: QrCodeDataToDomainMapper
    protected lateinit var cacheDataSource: QrCodesRepositoryTest.TestQrCodesCacheDataSource
    protected lateinit var manageResources: TestManageResources
    protected lateinit var saveInternalStorage: TestSaveInternalStorage

    @BeforeEach
    fun setUp() {
        cacheDataSource = QrCodesRepositoryTest.TestQrCodesCacheDataSource()
        manageResources = TestManageResources()
        mapper = QrCodeDataToDomainMapper()
        saveInternalStorage = TestSaveInternalStorage()
        repository = QrCodesRepository.Base(cacheDataSource, mapper, manageResources,saveInternalStorage)
    }
}