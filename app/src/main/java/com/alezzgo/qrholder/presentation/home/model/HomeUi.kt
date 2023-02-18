package com.alezzgo.qrholder.presentation.home.model

import android.view.View
import com.alezzgo.qrholder.databinding.FragmentHomeBinding
import com.alezzgo.qrholder.presentation.core.model.QrCodeUi
import com.alezzgo.qrholder.presentation.home.adapter.QrCodesAdapter

sealed class HomeUi {

    abstract fun show(
        adapter: QrCodesAdapter,
        binding: FragmentHomeBinding
    )

    object Loading : HomeUi() {
        override fun show(
            adapter: QrCodesAdapter,
            binding: FragmentHomeBinding
        ) {
            binding.run {
                toolbar.visibility = View.GONE
                rvQrList.visibility = View.GONE
                tvNothingWasFoundLayout.visibility = View.GONE
                ivNothingWasFound.visibility = View.GONE
                tvEmptyListLayout.visibility = View.GONE
                ivEmptyFolder.visibility = View.GONE
                tvError.visibility = View.GONE
                ivError.visibility = View.GONE
                shimmersLayout.visibility = View.VISIBLE
                shimmersLayout.startShimmer()
            }

        }

    }

    data class Success(
        private val qrCodes: List<QrCodeUi> = emptyList()
    ) : HomeUi() {
        override fun show(
            adapter: QrCodesAdapter,
            binding: FragmentHomeBinding
        ) {
            binding.run {
                shimmersLayout.stopShimmer()
                shimmersLayout.visibility = View.GONE
                toolbar.visibility = View.VISIBLE
                rvQrList.visibility = View.VISIBLE
                adapter.submitList(qrCodes)
                tvNothingWasFoundLayout.visibility = View.GONE
                ivNothingWasFound.visibility = View.GONE
                tvEmptyListLayout.visibility = View.GONE
                ivEmptyFolder.visibility = View.GONE
                tvError.visibility = View.GONE
                ivError.visibility = View.GONE
            }

        }

    }

    object Empty : HomeUi() {
        override fun show(
            adapter: QrCodesAdapter,
            binding: FragmentHomeBinding
        ) {
            binding.run {
                shimmersLayout.stopShimmer()
                shimmersLayout.visibility = View.GONE
                toolbar.visibility = View.GONE
                rvQrList.visibility = View.GONE
                tvNothingWasFoundLayout.visibility = View.GONE
                ivNothingWasFound.visibility = View.GONE
                tvError.visibility = View.GONE
                ivError.visibility = View.GONE
                tvEmptyListLayout.visibility = View.VISIBLE
                ivEmptyFolder.visibility = View.VISIBLE
            }
        }

    }

    object NothingWasFound : HomeUi() {
        override fun show(
            adapter: QrCodesAdapter,
            binding: FragmentHomeBinding
        ) {
            binding.run {
                shimmersLayout.stopShimmer()
                shimmersLayout.visibility = View.GONE
                toolbar.visibility = View.VISIBLE
                rvQrList.visibility = View.GONE
                tvNothingWasFoundLayout.visibility = View.VISIBLE
                ivNothingWasFound.visibility = View.VISIBLE
                tvError.visibility = View.GONE
                ivError.visibility = View.GONE
                tvEmptyListLayout.visibility = View.GONE
                ivEmptyFolder.visibility = View.GONE
            }
        }

    }

    data class Error(
        private val errorMessage: String
    ) : HomeUi() {
        override fun show(
            adapter: QrCodesAdapter,
            binding: FragmentHomeBinding
        ) {
            binding.run {
                shimmersLayout.stopShimmer()
                shimmersLayout.visibility = View.GONE
                toolbar.visibility = View.GONE
                rvQrList.visibility = View.GONE
                tvNothingWasFoundLayout.visibility = View.GONE
                ivNothingWasFound.visibility = View.GONE
                tvError.visibility = View.VISIBLE
                tvError.text = errorMessage
                ivError.visibility = View.VISIBLE
                tvEmptyListLayout.visibility = View.GONE
                ivEmptyFolder.visibility = View.GONE
            }
        }
    }

}
