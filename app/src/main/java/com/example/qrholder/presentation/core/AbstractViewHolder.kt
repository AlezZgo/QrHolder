package com.example.qrholder.presentation.core

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

typealias CreateViewHolder<B,VH> = (binding : B) -> VH

abstract class AbstractViewHolder<T, B : ViewBinding>(
    binding: B
) : RecyclerView.ViewHolder(binding.root), Bind<T>

