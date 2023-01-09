package com.example.qrholder.presentation.home.model

import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.example.qrholder.core.Match

data class QrCodeUi(
    private val title: String,
    private val content: String,
    private val path: String
) : Contains<String>, Match<QrCodeUi> {
    //todo override
    fun map(
        card: CardView,
        tvTitle: TextView,
        tvContent: TextView,
        ivQrCode: ImageView,
        onImageClick: (qrCode: QrCodeUi) -> Unit,
        onCardClick: (qrCode: QrCodeUi) -> Unit,
        onCardLongClick: (qrCode: QrCodeUi) -> Unit
    ) {
        tvTitle.text = title
        tvContent.text = content
        //Todo Glide to image
        card.setOnClickListener {
            onCardClick.invoke(this)
        }
        card.setOnLongClickListener {
            onCardLongClick.invoke(this)
            return@setOnLongClickListener true
        }
        ivQrCode.setOnClickListener {
            onImageClick.invoke(this)
        }



    }

    fun mapToImage(imageView : ImageView){

        Glide.with(imageView).load(path).into(imageView)

    }

    override fun contains(text: String) = title.contains(text, true) or content.contains(text, true)

    override fun matchesId(model: QrCodeUi) = model.title == title

    override fun matches(model: QrCodeUi) = model == this
}

interface Contains<T> {
    fun contains(text: T): Boolean
}


