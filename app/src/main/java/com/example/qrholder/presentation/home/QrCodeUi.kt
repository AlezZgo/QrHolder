package com.example.qrholder.presentation.home

import android.widget.TextView

data class QrCodeUi(
    private val title: String,
    private val content: String,
    private val path: String
) : Contains<String> {
    fun map(tvTitle: TextView, tvContent: TextView) {
        tvTitle.text = title
        tvContent.text = content
    }

    override fun contains(text: String) = title.contains(text, true) or content.contains(text, true)
}

interface Contains<T> {
    fun contains(text: T): Boolean
}
