package com.example.qrholder.presentation.onItemClickDialogFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.qrholder.databinding.DialogFragmentOnItemClickBinding
import com.example.qrholder.presentation.home.HomeViewModel

class OnItemClickDialogFragment : DialogFragment() {

    private val viewModel by activityViewModels<HomeViewModel>()

    private lateinit var binding: DialogFragmentOnItemClickBinding

    private val args by navArgs<OnItemClickDialogFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogFragmentOnItemClickBinding.inflate(inflater)
        binding.run {
            args.qrCode.run {
                share.setOnClickListener {
                    this.share(binding.root.context)
                    dismiss()
                }
                browse.setOnClickListener {
                    this.browse(binding.root.context)
                    dismiss()
                }
                delete.setOnClickListener {
                    viewModel.delete(this)
                    dismiss()
                }
                edit.setOnClickListener {
                    //todo
                    dismiss()
                }
            }
        }
        return binding.root
    }

}