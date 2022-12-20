package com.example.qrholder.presentation.home

import com.example.qrholder.databinding.RvItemHomeQrcodeBinding
import com.example.qrholder.presentation.core.AbstractListAdapter
import com.example.qrholder.presentation.home.mapper.QrCodeCardViewHolder

class QrCodesAdapter : AbstractListAdapter<QrCodeUi, RvItemHomeQrcodeBinding, QrCodeCardViewHolder>(
    RvItemHomeQrcodeBinding::inflate,
    QrCodeCardViewHolder::new,
    QrCodeDiffUtilCallback(),
)
