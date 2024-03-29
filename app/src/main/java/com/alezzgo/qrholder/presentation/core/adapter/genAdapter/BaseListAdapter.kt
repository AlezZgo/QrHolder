package com.alezzgo.qrholder.presentation.core.adapter.genAdapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.alezzgo.qrholder.core.Match
import com.alezzgo.qrholder.presentation.core.adapter.AbstractDiffCallback

abstract class BaseListAdapter<D : Match<D>, VH : BaseViewHolder<D>>(
    diff: AbstractDiffCallback<D>,
) : ListAdapter<D, VH>(diff) {

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }
}