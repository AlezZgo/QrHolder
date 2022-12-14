package com.example.qrholder.home.ui

import com.example.qrholder.presentation.home.mapper.CompleteListMapper
import com.example.qrholder.presentation.home.HomeUiState
import com.example.qrholder.presentation.home.QrCodeUi
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CompleteListMapperTest{

    private val homeUiCommunication = BaseViewModelTest.TestHomeUiStateCommunication()

    private val errorMessage = "Something went wrong"
    private val mapper = CompleteListMapper()
    private val complicatedFilter = "ddlkrjigosdpfoiskepofjspoeifsepo"
    private val filter = "d"
    private val testList = listOf(
        QrCodeUi("Cat", "www.cat.com",path = "content.cat.id1"),
        QrCodeUi("Dog", "www.dog.com",path = "content.cat.id2"),
        QrCodeUi("Duck", "www.duck.com",path = "content.cat.id3"),
        QrCodeUi("Whale", "www.whale_digger.com",path = "content.cat.id4"),
    )

    @Test
    fun `handle error message`(){

        mapper.map(
            testList,
            errorMessage,
            filter,
            homeUiCommunication
        )

        assertEquals(1,homeUiCommunication.homeUiStateCalledList.size)
        assertEquals(HomeUiState.Error(errorMessage),homeUiCommunication.homeUiStateCalledList[0])
    }

    @Test
    fun `handle empty list`(){

        mapper.map(
            emptyList(),
            "",
            filter,
            homeUiCommunication
        )

        assertEquals(1,homeUiCommunication.homeUiStateCalledList.size)
        assertEquals(HomeUiState.Empty,homeUiCommunication.homeUiStateCalledList[0])
    }

    @Test
    fun `handle list with complicated filter, but filtered list is empty`(){

        mapper.map(
            testList,
            "",
            complicatedFilter,
            homeUiCommunication
        )

        assertEquals(1,homeUiCommunication.homeUiStateCalledList.size)
        assertEquals(HomeUiState.NothingWasFound,homeUiCommunication.homeUiStateCalledList[0])
    }

    @Test
    fun `handle list with filter`(){

        mapper.map(
            testList,
            "",
            filter,
            homeUiCommunication
        )

        assertEquals(1,homeUiCommunication.homeUiStateCalledList.size)
        assertEquals(
            HomeUiState.Success(
            listOf(
                QrCodeUi("Dog", "www.dog.com",path = "content.cat.id2"),
                QrCodeUi("Duck", "www.duck.com",path = "content.cat.id3"),
                QrCodeUi("Whale", "www.whale_digger.com",path = "content.cat.id4")
            )
        ),homeUiCommunication.homeUiStateCalledList[0])
    }

}