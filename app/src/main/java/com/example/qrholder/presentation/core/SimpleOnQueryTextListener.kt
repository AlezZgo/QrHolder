package com.example.qrholder.presentation.core

import android.widget.SearchView

class SimpleOnQueryTextListener(
    private val onQueryTextChanged: (newText: String) -> Unit
) : SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(newText: String) = false

    override fun onQueryTextChange(newText: String): Boolean {
        onQueryTextChanged.invoke(newText)
        return false
    }
}