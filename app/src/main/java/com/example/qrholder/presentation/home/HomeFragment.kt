package com.example.qrholder.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.qrholder.databinding.FragmentHomeBinding
import com.example.qrholder.presentation.core.AbstractFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment(
    private val uiStateMapper : HomeUiState.Mapper<Unit>
) : AbstractFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate,
    HomeViewModel::class.java
){

    private val adapter by lazy {
        //todo inject QrCodeDiffUtilCallback
        QrCodesAdapter(QrCodeDiffUtilCallback())
    }

    override fun setupViews() {
        super.setupViews()
        binding.rvQrList.adapter = adapter
    }

    override fun observe() {
        viewModel.observeUiState(viewLifecycleOwner){
            it.map(uiStateMapper)
        }
    }
}