package com.example.qrholder.presentation.home.domain

import com.example.qrholder.domain.HomeInteractor
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