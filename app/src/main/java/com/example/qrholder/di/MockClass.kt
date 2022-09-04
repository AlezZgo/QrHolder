package com.example.qrholder.di

import javax.inject.Inject

class MockClass @Inject constructor(
    private val msc1: MockSubClass1,
    private val msc2 : MockSubClass2,
    private val msc3 : MockSubClass3) {
    fun test() : String = "${msc1.foo()} ${msc2.foo()} ${msc3.foo()}"
}

class MockSubClass1 {
    fun foo() : String = "MockSubClass1"
}

class MockSubClass2 {
    fun foo() : String = "MockSubClass2"
}

class MockSubClass3 {
    fun foo(): String = "MockSubClass3"
}