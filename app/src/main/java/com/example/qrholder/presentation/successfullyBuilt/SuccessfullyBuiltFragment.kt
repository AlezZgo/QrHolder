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
        with(args.qrCode) {
            loadImage(binding.ivQrCode)
            loadTitle(binding.tvTitle)
            loadContent(binding.tvContent)
        }
    }

    override fun setupListeners() {
        super.setupListeners()
        binding.btnOk.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}

