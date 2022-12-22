package com.example.qrholder.presentation.home.model

import android.widget.TextView
import com.example.qrholder.core.Match

data class QrCodeUi(
    private val title: String,
    private val content: String,
    private val path: String
) : Contains<String>, Match<QrCodeUi> {
    fun map(tvTitle: TextView, tvContent: TextView) {
        tvTitle.text = title
        tvContent.text = content
    }

    override fun contains(text: String) = title.contains(text, true) or content.contains(text, true)

    override fun matchesId(model: QrCodeUi) = model.title == title

    override fun matches(model: QrCodeUi) = model == this
}

interface Contains<T> {
    fun contains(text: T): Boolean
}


