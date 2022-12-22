package com.example.qrholder.presentation.core.threeActionsAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.example.qrholder.core.Match
import com.example.qrholder.presentation.core.AbstractDiffCallback
import com.example.qrholder.presentation.core.Inflate
import com.example.qrholder.presentation.core.simpleAdapter.AbstractSimpleViewHolder
import com.example.qrholder.presentation.core.simpleAdapter.CreateViewHolder
import com.example.qrholder.presentation.home.model.QrCodeUi

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
        holder.bind(getItem(position),actionOne,actionTwo,actionThree)

}