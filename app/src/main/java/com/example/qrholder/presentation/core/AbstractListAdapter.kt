package com.example.qrholder.presentation.core

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.example.qrholder.core.Match

abstract class AbstractListAdapter<
        M : Match<M>,
        B : ViewBinding,
        VH : AbstractViewHolder<M, B>>(
    diffUtil: AbstractDiffCallback<M>,
    private val inflate: Inflate<B>,
    private val viewHolder: CreateViewHolder<B,VH>
) : ListAdapter<M, VH>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        viewHolder(inflate.invoke(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) =
        holder.bind(getItem(position))

}