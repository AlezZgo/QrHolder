package com.example.qrholder.presentation.core.adapter.simpleAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.example.qrholder.core.Match
import com.example.qrholder.presentation.core.adapter.AbstractDiffCallback
import com.example.qrholder.presentation.core.fragment.Inflate

abstract class AbstractSimpleListAdapter<
        M : Match<M>,
        B : ViewBinding,
        VH : AbstractSimpleViewHolder<M, B>>(
    private val inflate: Inflate<B>,
    private val viewHolder: CreateViewHolder<B, VH>,
    diffUtil: AbstractDiffCallback<M>,
) : ListAdapter<M, VH>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        viewHolder(inflate.invoke(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) =
        holder.bind(getItem(position))

}