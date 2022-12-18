package com.example.qrholder.home.ui

import com.example.qrholder.presentation.home.model.QrCodeUi
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

internal class QrCodeUiTest {

    @Test
    fun contains() {
        assertEquals(false, QrCodeUi("Cat", "www.cat.com","content.cat.id1").contains("d"))
        assertEquals(true, QrCodeUi("Dog", "www.dog.com","content.cat.id2").contains("d"))
        assertEquals(true, QrCodeUi("Duck", "www.duck.com","content.cat.id3").contains("d"))
        assertEquals(true, QrCodeUi("Whale", "www.whale_digger.com","content.cat.id4").contains("d"))
    }
}