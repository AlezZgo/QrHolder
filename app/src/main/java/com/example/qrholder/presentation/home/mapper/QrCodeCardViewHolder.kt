package com.example.qrholder.presentation.home.mapper

import com.example.qrholder.databinding.RvItemHomeQrcodeBinding
import com.example.qrholder.presentation.core.AbstractViewHolder
import com.example.qrholder.presentation.home.QrCodeUi

class QrCodeCardViewHolder(
    private val binding: RvItemHomeQrcodeBinding
) : AbstractViewHolder<QrCodeUi, RvItemHomeQrcodeBinding>(binding) {

    override fun bind(model: QrCodeUi) =
        model.map(binding.titleTextView, binding.contentTextView)

    companion object{
        fun new(binding: RvItemHomeQrcodeBinding) = QrCodeCardViewHolder(binding)
    }

}