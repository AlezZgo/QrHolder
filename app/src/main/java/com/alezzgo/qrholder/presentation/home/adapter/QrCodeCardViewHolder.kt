package com.alezzgo.qrholder.presentation.home.adapter

import com.alezzgo.qrholder.databinding.RvItemHomeQrcodeBinding
import com.alezzgo.qrholder.presentation.core.adapter.threeActionsAdapter.AbstractThreeActionsViewHolder
import com.alezzgo.qrholder.presentation.core.model.QrCodeUi

class QrCodeCardViewHolder(
    private val binding: RvItemHomeQrcodeBinding
) : AbstractThreeActionsViewHolder<QrCodeUi, RvItemHomeQrcodeBinding>(binding) {

    override fun bind(
        model: QrCodeUi,
        actionOne: (qrCode: QrCodeUi) -> Unit,
        actionTwo: (qrCode: QrCodeUi) -> Unit,
        actionThree: (qrCode: QrCodeUi) -> Unit
    ) = model.map(
        binding.root,
        binding.tvTitle,
        binding.tvContent,
        binding.ivQrCode,
        actionOne,
        actionTwo,
        actionThree
    )

    companion object {
        fun new(binding: RvItemHomeQrcodeBinding) = QrCodeCardViewHolder(binding)
    }

}