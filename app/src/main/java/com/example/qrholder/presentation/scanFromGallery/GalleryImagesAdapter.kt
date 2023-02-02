package com.example.qrholder.presentation.scanFromGallery

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.qrholder.databinding.GalleryImageItemBinding
import com.example.qrholder.presentation.core.adapter.AbstractDiffCallback
import com.example.qrholder.presentation.core.adapter.genAdapter.BaseListAdapter
import com.example.qrholder.presentation.core.adapter.genAdapter.BaseViewHolder

class GalleryImagesAdapter(
    private val onImageClick: (image: GalleryImageUi) -> Unit
) : BaseListAdapter<GalleryImageUi, GalleryImageViewHolder>(GalleryImageDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryImageViewHolder {
        return GalleryImageViewHolder(
            GalleryImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onImageClick
        )
    }
}

class GalleryImageViewHolder(
    private val binding: GalleryImageItemBinding,
    private val onImageClick: (image: GalleryImageUi) -> Unit
) : BaseViewHolder<GalleryImageUi>(binding) {

    override fun bind(item: GalleryImageUi) =
        item.show(binding.ivGalleryImage, binding.llImageContainer,onImageClick)

}

class GalleryImageDiffUtilCallback : AbstractDiffCallback<GalleryImageUi>()
