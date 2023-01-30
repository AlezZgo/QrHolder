package com.example.qrholder.presentation.scanFromGallery

import android.widget.ImageView
import android.widget.LinearLayout
import com.example.qrholder.R
import com.example.qrholder.core.Match
import com.example.qrholder.core.setDebounceClickListener
import com.example.qrholder.presentation.core.ObserveUiState
import com.example.qrholder.presentation.core.loadImage.load

data class GalleryImageUi(
    private val path: String,
    private val selected: Boolean
) : Match<GalleryImageUi>, Opposite<GalleryImageUi> {

    override fun matches(model: GalleryImageUi) = model == this

    override fun matchesId(model: GalleryImageUi) = model.path == this.path

    fun show(
        ivGalleryImage: ImageView,
        container: LinearLayout,
        onImageClick: (image: GalleryImageUi) -> Unit
    ) {
        ivGalleryImage.load(R.drawable.ic_app)

        ivGalleryImage.setDebounceClickListener {
            onImageClick.invoke(this)
        }

        container.setBackgroundColor(
            ivGalleryImage.context.getColor(
                if (selected)
                    R.color.aquamarine_500
                else
                    R.color.white
            )
        )

    }

    override fun opposite() = this.copy(selected = !selected)

    fun unselected() = this.copy(selected = false)
}

interface Opposite<T>{
    fun opposite() : T
}