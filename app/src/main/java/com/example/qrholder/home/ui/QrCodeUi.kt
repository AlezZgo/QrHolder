package com.example.qrholder.home.ui

import android.widget.TextView

data class QrCodeUi(
    private val title: String,
    private val content: String,
) : Contains<String>{
    fun map(tvTitle: TextView, tvContent: TextView) {
        tvTitle.text = title
        tvContent.text = content
    }

    override fun contains(content: String) = title.contains(content) or content.contains(content)
}

interface Contains<T> {
    fun contains(content : T) : Boolean
}
