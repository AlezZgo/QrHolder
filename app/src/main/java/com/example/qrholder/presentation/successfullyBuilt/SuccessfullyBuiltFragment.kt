package com.example.qrholder.presentation.successfullyBuilt

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.qrholder.databinding.FragmentSuccessfullyBuiltBinding
import com.example.qrholder.presentation.core.fragment.AbstractFragment

class SuccessfullyBuiltFragment :
    AbstractFragment<FragmentSuccessfullyBuiltBinding, SuccessfullyBuiltViewModel>(
        FragmentSuccessfullyBuiltBinding::inflate, SuccessfullyBuiltViewModel::class.java
    ) {
    private val args by navArgs<SuccessfullyBuiltFragmentArgs>()

    override fun setupViews() {
        super.setupViews()
        args.qrCode.loadImage(binding.ivQrCode)
        args.qrCode.loadTitle(binding.tvTitle)
        args.qrCode.loadContent(binding.tvContent)
    }

    override fun setupListeners() {
        super.setupListeners()
        binding.BtnOk.setOnClickListener {
            findNavController().navigate(SuccessfullyBuiltFragmentDirections.actionSuccessfullyBuiltFragmentToNavigationHome())
        }
    }
}

