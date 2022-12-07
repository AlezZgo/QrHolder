package com.example.qrholder.home.ui

import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

internal class QrCodeUiTest {


    @Test
    fun contains() {
        assertEquals(false, QrCodeUi("Cat", "www.cat.com").contains("d"))
        assertEquals(true, QrCodeUi("Dog", "www.dog.com").contains("d"))
        assertEquals(true, QrCodeUi("Duck", "www.duck.com").contains("d"))
        assertEquals(true, QrCodeUi("Whale", "www.whale_digger.com").contains("d"))
    }
}