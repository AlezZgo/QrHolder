package com.example.qrholder.presentation.home.adapter

import com.example.qrholder.databinding.RvItemHomeQrcodeBinding
import com.example.qrholder.presentation.core.AbstractListAdapter
import com.example.qrholder.presentation.home.model.QrCodeUi

class QrCodesAdapter : AbstractListAdapter<QrCodeUi, RvItemHomeQrcodeBinding, QrCodeCardViewHolder>(
    RvItemHomeQrcodeBinding::inflate,
    QrCodeCardViewHolder::new,
    QrCodeDiffUtilCallback(),
)
