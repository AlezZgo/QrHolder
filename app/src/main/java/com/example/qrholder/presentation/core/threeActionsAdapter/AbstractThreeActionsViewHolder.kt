package com.example.qrholder.presentation.core.threeActionsAdapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * ViewHolder with only model and no any listeners
 */
abstract class AbstractThreeActionsViewHolder<T, B : ViewBinding>(
    binding: B
) : RecyclerView.ViewHolder(binding.root), BindWithThreeActions<T>

