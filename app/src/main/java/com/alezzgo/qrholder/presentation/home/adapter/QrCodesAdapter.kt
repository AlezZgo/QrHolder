package com.alezzgo.qrholder.presentation.home.adapter

import com.alezzgo.qrholder.databinding.RvItemHomeQrcodeBinding
import com.alezzgo.qrholder.presentation.core.adapter.threeActionsAdapter.AbstractThreeActionsListAdapter
import com.alezzgo.qrholder.presentation.core.model.QrCodeUi

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
