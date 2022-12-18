package com.example.qrholder.presentation.home.adapter

import com.example.qrholder.databinding.RvItemHomeQrcodeBinding
import com.example.qrholder.presentation.core.threeActionsAdapter.AbstractThreeActionsListAdapter
import com.example.qrholder.presentation.home.model.QrCodeUi

class QrCodesAdapter(
    onImageClick: (qrCode: QrCodeUi) -> Unit,
    onCardClick: (qrCode: QrCodeUi) -> Unit,
    onCardLongClick: (qrCode: QrCodeUi) -> Unit
) : AbstractThreeActionsListAdapter<QrCodeUi, RvItemHomeQrcodeBinding, QrCodeCardViewHolder>(
    onImageClick,
    onCardClick,
    onCardLongClick,
    RvItemHomeQrcodeBinding::inflate,
    QrCodeCardViewHolder::new,
    QrCodeDiffUtilCallback(),
)
