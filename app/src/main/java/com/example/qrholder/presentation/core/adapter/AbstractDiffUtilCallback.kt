package com.example.qrholder.presentation.core.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.qrholder.core.Match

abstract class AbstractDiffCallback<T : Match<T>> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.matchesId(newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem.matches(newItem)

}