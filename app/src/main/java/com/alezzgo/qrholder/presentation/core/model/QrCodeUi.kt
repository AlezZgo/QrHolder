package com.alezzgo.qrholder.presentation.core.model

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import com.alezzgo.qrholder.core.Delete
import com.alezzgo.qrholder.core.Match
import com.alezzgo.qrholder.domain.FetchQrCode
import com.alezzgo.qrholder.presentation.core.loadImage.loadWithoutCaching
import com.alezzgo.qrholder.presentation.editQrCode.Edit
import kotlinx.parcelize.Parcelize


@Parcelize
data class QrCodeUi(
    private val title: String,
    private val content: String,
    private val path: String
) : Contains<String>, Match<QrCodeUi>, Parcelable {
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
        ivQrCode.loadWithoutCaching(path)
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

    suspend fun delete(delete: Delete<String>) = delete.delete(title)

    fun edit(editor: Edit) = editor.edit(title)

    fun share(context: Context) {
        val shareIntent = Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, content);
        shareIntent.type = "text/plain";
        startActivity(context, shareIntent, Bundle());
    }

    fun browse(context: Context) {
        val intent = Intent(Intent.ACTION_WEB_SEARCH)
        intent.putExtra(SearchManager.QUERY, content) // query contains search string
        startActivity(context, intent, Bundle())
    }

    suspend fun reload(loader: FetchQrCode<QrCodeUi>) = loader.fetchQrCode(title = title)

    fun loadImage(imageView: ImageView) = imageView.loadWithoutCaching(path)

    fun loadTitle(textView: TextView) {
        textView.text = title
    }

    fun loadTitle(editText: EditText) {
        editText.setText(title)
    }

    fun loadContent(textView: TextView) {
        textView.text = content
    }

    fun loadContent(editText: EditText) {
        editText.setText(content)
    }

    override fun contains(text: String) = title.contains(text, true) or content.contains(text, true)

    override fun matchesId(model: QrCodeUi) = model.title == title

    override fun matches(model: QrCodeUi) = model == this

}

interface Contains<T> {
    fun contains(text: T): Boolean
}


