package com.example.qrholder.presentation.home

import com.example.qrholder.databinding.FragmentHomeBinding
import com.example.qrholder.presentation.core.AbstractFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : AbstractFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate,
    HomeViewModel::class.java
) {

    private val adapter by lazy {
        //todo inject QrCodeDiffUtilCallback
        QrCodesAdapter(QrCodeDiffUtilCallback())
    }

    override fun setupViews() {
        super.setupViews()
    }

    override fun observe() {
        viewModel.observeUiState(viewLifecycleOwner) {
            //Todo it.map(mapper)
        }
    }
}