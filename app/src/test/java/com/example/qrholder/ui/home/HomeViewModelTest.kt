package com.example.qrholder.ui.home

import androidx.lifecycle.viewmodel.CreationExtras
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class HomeViewModelTest {

    @Test
    fun `first run application with Empty qrCode list`() {

        val communications = TestHomeCommunications()
        val interactor = TestHomeInteractor()

        val viewModel = HomeViewModel(communications, interactor)

        assertEquals(1, communications.uiStateCalledList.size)
        assertEquals(HomeUiState.Loading, communications.uiStateCalledList[0])

        assertEquals(0, interactor.resultList.size)

        viewModel.init(isFirstRun = true)

        assertEquals(1, interactor.resultList.size)
        assertEquals(QrCodes.Empty, interactor.resultList[0])

        assertEquals(2, communications.uiStateCalledList.size)
        assertEquals(HomeUiState.Empty, communications.uiStateCalledList[1])

    }

    @Test
    fun `get qrCode list then re-init`() {

        val communications = TestHomeCommunications()
        val interactor = TestHomeInteractor()
        interactor.resultList[0] = QrCodes.Success(/* 3 models QrCode */)
        val viewModel = HomeViewModel(communications, interactor)

        assertEquals(1, communications.uiStateCalledList.size)
        assertEquals(HomeUiState.Loading, communications.uiStateCalledList[0])

        assertEquals(0, interactor.resultList.size)

        viewModel.fetchAll()

        assertEquals(1, interactor.resultList.size)
        assertEquals(QrCodes.Success(/* 3 models QrCode */), interactor.resultList[0])

        assertEquals(2, communications.uiStateCalledList.size)
        assertEquals(HomeUiState.Success(/* 3 models QrCode */), communications.uiStateCalledList[1])

        viewModel.init(isFirstRun = false)

        assertEquals(1, interactor.resultList.size)
        assertEquals(QrCodes.Success(/* 3 models QrCode */), interactor.resultList[0])

        assertEquals(3, communications.uiStateCalledList.size)
        assertEquals(HomeUiState.Success(/* 3 models QrCode */), communications.uiStateCalledList[2])

    }

    @Test
    fun `Enter some text in the filter input field and get list filtered then re-init`() {
        val communications = TestHomeCommunications()
        val interactor = TestHomeInteractor()
        interactor.resultList[0] = QrCodes.Success(/* 3 models QrCode */)
        communications.uiStateCalledList[0] = HomeUiState.Success(/* 3 models QrCode */)
        val viewModel = HomeViewModel(communications, interactor)

        assertEquals(1,communications.filterCalledList.size)
        assertEquals(TestHomeCommunications.EMPTY_FIELD,communications.filterCalledList[0])

        val testFilter = "Hotel"
        viewModel.filter(testFilter)

        assertEquals(2, interactor.resultList.size)
        assertEquals(QrCodes.Success(/* 1 QrCode model  */), interactor.resultList[1])

        assertEquals(2, communications.uiStateCalledList.size)
        assertEquals(HomeUiState.Success(/* 1 QrCode model  */), communications.uiStateCalledList[1])

        assertEquals(2,communications.filterCalledList.size)
        assertEquals(testFilter,communications.filterCalledList[1])

        viewModel.init(isFirstRun = false)

        assertEquals(2, interactor.resultList.size)
        assertEquals(QrCodes.Success(/* 1 QrCode model  */), interactor.resultList[1])

        assertEquals(3, communications.uiStateCalledList.size)
        assertEquals(HomeUiState.Success(/* 1 QrCode model  */), communications.uiStateCalledList[2])
        assertEquals(testFilter,communications.filterCalledList[1])

    }

    @Test
    fun `Enter some text in the filter input field but nothing was found`() {
        val communications = TestHomeCommunications()
        val interactor = TestHomeInteractor()
        interactor.resultList[0] = QrCodes.Success(/* 3 models QrCode */)
        communications.uiStateCalledList[0] = HomeUiState.Success(/* 3 models QrCode */)
        val viewModel = HomeViewModel(communications, interactor)

        val testFilter = "I entered something that I definitely don't have in my qr code list"
        viewModel.filter(testFilter)

        assertEquals(2, interactor.resultList.size)
        assertEquals(QrCodes.Success(/* 1 QrCode model  */), interactor.resultList[1])

        assertEquals(2, communications.uiStateCalledList.size)
        assertEquals(HomeUiState.Success(/* 1 QrCode model  */), communications.uiStateCalledList[1])
        assertEquals(testFilter,communications.filterCalledList[1])

        viewModel.init(isFirstRun = false)

        assertEquals(3, communications.uiStateCalledList.size)
        assertEquals(HomeUiState.Success(/* 1 QrCode model  */), communications.uiStateCalledList[2])

        assertEquals(2, interactor.resultList.size)
        assertEquals(QrCodes.Success(/* 1 QrCode model  */), interactor.resultList[1])
    }


    private class TestHomeInteractor {

        val resultList = mutableListOf<QrCodes> (QrCodes.Empty)

        override suspend fun fetchAll(filter : String): QrCodes {
            resultList.add(QrCodes)
        }
    }
}

private class TestHomeCommunications : HomeCommunications {

    val uiStateCalledList = mutableListOf<HomeUiState>(HomeUiState.Loading)
    val filterCalledList = mutableListOf(EMPTY_FIELD)

    override fun showState(state: HomeUiState) {
        uiStateCalledList.add(state)
    }

    override fun filter(text : String){
        filterCalledList.add(text)
    }

    companion object{
        const val EMPTY_FIELD = ""
    }

}