package com.example.qrholder.presentation.buildQrCode

import com.example.qrholder.di.EmptyText

data class QrCodeInBuild(
    @EmptyText private var title : String,
    @EmptyText private var content : String,
) : ChangeTitle,ChangeContent {

    override fun changeTitle(title: String) {
        this.title = title
    }

    override fun changeContent(content: String) {
        this.content = content
    }

}