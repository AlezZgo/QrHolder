package com.example.qrholder.presentation.core

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.qrholder.presentation.home.Bind


abstract class AbstractViewHolder<T, B : ViewBinding>(
    binding: B
) : RecyclerView.ViewHolder(binding.root), Bind<T>

