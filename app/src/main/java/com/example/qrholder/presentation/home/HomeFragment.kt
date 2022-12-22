package com.example.qrholder.presentation.home

import com.example.qrholder.databinding.FragmentHomeBinding
import com.example.qrholder.presentation.core.AbstractFragment
import com.example.qrholder.presentation.home.adapter.QrCodesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : AbstractFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate,
    HomeViewModel::class.java
) {

    private val adapter by lazy {
        QrCodesAdapter()
    }

    override fun setupViews() {
        super.setupViews()
        binding.rvQrList.adapter = adapter
    }

    override fun observe() {
        viewModel.observeUiState(viewLifecycleOwner) { uiState ->
            with(binding) {
                uiState.show(
                    toolbar = toolbar,
                    recyclerView = rvQrList,
                    adapter = adapter,
                    nothingWasFoundTextView = tvNothingWasFoundLayout,
                    nothingWasFoundImageView = ivNothingWasFound,
                    emptyListTextView = tvEmptyListLayout,
                    emptyListImageView = ivEmptyFolder,
                    tvError = tvError,
                    ivError = ivError,
                    shimmers = shimmersLayout
                )
            }

        }
    }
}