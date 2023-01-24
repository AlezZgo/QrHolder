package com.example.qrholder.presentation.confirmDialog

import android.os.Parcelable
import android.view.View
import android.widget.TextView
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConfirmDialogInitState(
    private val title: String,
    private val positiveAction: () -> Unit,
    private val negativeAction: () -> Unit,
) : Parcelable {

    fun show(
        textView: TextView,
        onPositiveClickView: View,
        onNegativeClickView: View,
        onPostClick : () -> Unit
    ) {
        textView.text = title
        onPositiveClickView.setOnClickListener {
            //todo fix crash
            positiveAction.invoke()
            onPostClick.invoke()
        }
        onNegativeClickView.setOnClickListener {
            negativeAction.invoke()
            onPostClick.invoke()
        }
    }
}