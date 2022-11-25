package com.example.qrholder.home.ui

import android.widget.TextView

data class QrCodeUi(
    private val title: String,
    private val content: String,
) {
    fun map(tvTitle: TextView, tvContent: TextView) {
        tvTitle.text = title
        tvContent.text = content
    }
}