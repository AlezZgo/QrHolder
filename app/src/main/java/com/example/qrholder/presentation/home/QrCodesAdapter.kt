package com.example.qrholder.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.qrholder.databinding.RvItemHomeQrcodeBinding
import com.example.qrholder.presentation.core.AbstractDiffCallback

class QrCodesAdapter(
    diffUtil: AbstractDiffCallback<QrCodeUi>,
) : ListAdapter<QrCodeUi, QrCodesAdapter.QrCodeCardViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = QrCodeCardViewHolder(
        RvItemHomeQrcodeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: QrCodeCardViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class QrCodeCardViewHolder(
        private val binding: RvItemHomeQrcodeBinding
    ) : RecyclerView.ViewHolder(binding.root), Bind<QrCodeUi> {

        override fun bind(model: QrCodeUi): Unit =
            model.map(binding.titleTextView, binding.contentTextView)
    }

}

interface Bind<T> {

    fun bind(model: T)
}

class QrCodeDiffUtilCallback : AbstractDiffCallback<QrCodeUi>()

