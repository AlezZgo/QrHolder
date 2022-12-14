package com.example.qrholder.core

class TestManageResources : ManageResources {
    private var value = ""

    fun changeExpected(string: String) {
        value = string
    }

    override fun string(id: Int): String = value
}