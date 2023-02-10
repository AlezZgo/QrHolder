package com.alezzgo.qrholder.presentation.core.adapter.threeActionsAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.alezzgo.qrholder.core.Match
import com.alezzgo.qrholder.presentation.core.adapter.AbstractDiffCallback
import com.alezzgo.qrholder.presentation.core.adapter.simpleAdapter.CreateViewHolder
import com.alezzgo.qrholder.presentation.core.fragment.Inflate

abstract class AbstractThreeActionsListAdapter<
        M : Match<M>,
        B : ViewBinding,
        VH : AbstractThreeActionsViewHolder<M, B>>(
    private val actionOne: (qrCode: M) -> Unit,
    private val actionTwo: (qrCode: M) -> Unit,
    private val actionThree: (qrCode: M) -> Unit,
    private val inflate: Inflate<B>,
    private val viewHolder: CreateViewHolder<B, VH>,
    diffUtil: AbstractDiffCallback<M>,
) : ListAdapter<M, VH>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        viewHolder(inflate.invoke(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) =
        holder.bind(getItem(position), actionOne, actionTwo, actionThree)

}