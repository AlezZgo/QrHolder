package com.example.qrholder.presentation.buildQrCode

import com.example.qrholder.core.Mapper
import com.example.qrholder.di.ContentTextMapperAnnotation
import com.example.qrholder.di.EmptyText
import com.example.qrholder.di.TitleTextMapperAnnotation
import javax.inject.Inject

data class QrCodeBuilder @Inject constructor(
    @EmptyText private var titleText: String,
    @EmptyText private var contentText: String,
    private val communications: BuildQrCodeCommunications,
    @TitleTextMapperAnnotation private val titleMapper: Mapper<String, Unit>,
    @ContentTextMapperAnnotation private val contentMapper: Mapper<String, Unit>,

) : Build, ChangeTitle, ChangeContent {

    override fun build() {
        titleMapper.map(titleText)
        contentMapper.map(contentText)
        if(communications.isError())
            return
        else{
//            val myBitmap = QRCode.from("www.example.org").bitmap();
                //todo generate qr code, save to internal storage and put it into communications



        }

    }

    override fun changeTitle(title: String) {
        titleText = title
    }

    override fun changeContent(content: String) {
        contentText = content
    }
}
