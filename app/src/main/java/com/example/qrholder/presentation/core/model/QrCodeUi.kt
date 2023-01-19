package com.example.qrholder.presentation.core.model

import android.os.Parcelable
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.qrholder.core.Match
import com.example.qrholder.presentation.core.loadImage.load
import kotlinx.parcelize.Parcelize

@Parcelize
data class QrCodeUi(
    private val title: String,
    private val content: String,
    private val path: String
) : Contains<String>, Match<QrCodeUi>,Parcelable {
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
        ivQrCode.load(path)
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

    fun loadImage(imageView : ImageView) = imageView.load(path)

    fun loadTitle(textView : TextView) { textView.text = title }

    fun loadContent(textView : TextView) { textView.text = content }

    override fun contains(text: String) = title.contains(text, true) or content.contains(text, true)

    override fun matchesId(model: QrCodeUi) = model.title == title

    override fun matches(model: QrCodeUi) = model == this
}

interface Contains<T> {
    fun contains(text: T): Boolean
}


