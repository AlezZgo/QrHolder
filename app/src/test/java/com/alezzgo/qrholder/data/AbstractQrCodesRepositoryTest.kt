package com.alezzgo.qrholder.data

import com.alezzgo.qrholder.core.TestManageResources
import com.alezzgo.qrholder.data.mapper.QrCodeDataToDomainMapper
import org.junit.jupiter.api.BeforeEach

abstract class AbstractQrCodesRepositoryTest {

    protected lateinit var repository: QrCodesRepository
    protected lateinit var mapper: QrCodeDataToDomainMapper
    protected lateinit var cacheDataSource: QrCodesRepositoryTest.TestQrCodesCacheDataSource
    protected lateinit var manageResources: TestManageResources
    protected lateinit var saveInternalStorage: TestSaveInternalStorage
    protected lateinit var deleteInternalStorage: DeleteInternalStorage

    @BeforeEach
    fun setUp() {
        cacheDataSource = QrCodesRepositoryTest.TestQrCodesCacheDataSource()
        manageResources = TestManageResources()
        mapper = QrCodeDataToDomainMapper()
        saveInternalStorage = TestSaveInternalStorage()
        deleteInternalStorage = TestDeleteInternalStorage()
        repository = QrCodesRepository.Base(
            cacheDataSource,
            mapper,
            manageResources,
            saveInternalStorage,
            deleteInternalStorage
        )
    }
}