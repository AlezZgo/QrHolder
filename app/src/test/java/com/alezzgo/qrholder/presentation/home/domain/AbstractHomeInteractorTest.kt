package com.alezzgo.qrholder.presentation.home.domain

import com.alezzgo.qrholder.domain.HomeInteractor
import org.junit.jupiter.api.BeforeEach

abstract class AbstractHomeInteractorTest {

    protected lateinit var interactor: HomeInteractor
    protected lateinit var repository: TestQrCodesRepository

    @BeforeEach
    fun setUp() {
        repository = TestQrCodesRepository()
        interactor = HomeInteractor.Base(repository)
    }
}