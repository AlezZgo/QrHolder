package com.example.qrholder.presentation.core.simpleAdapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.qrholder.presentation.core.threeActionsAdapter.BindWithThreeActions

typealias CreateViewHolder<B,VH> = (binding : B) -> VH

/**
 * ViewHolder with only model and no any listeners
 */
abstract class AbstractSimpleViewHolder<T, B : ViewBinding>(
    binding: B
) : RecyclerView.ViewHolder(binding.root), Bind<T>

