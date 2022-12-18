package com.example.qrholder.presentation.home

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.appbar.MaterialToolbar

sealed class HomeUiState {

    abstract fun show(
        toolbar: MaterialToolbar,
        recyclerView: RecyclerView,
        adapter: QrCodesAdapter,
        nothingWasFoundTextView: TextView,
        nothingWasFoundImageView: ImageView,
        emptyListTextView: TextView,
        emptyListImageView: ImageView,
        tvError: TextView,
        ivError: ImageView,
        shimmers: ShimmerFrameLayout,
    )

    object Loading : HomeUiState() {
        override fun show(
            toolbar: MaterialToolbar,
            recyclerView: RecyclerView,
            adapter: QrCodesAdapter,
            nothingWasFoundTextView: TextView,
            nothingWasFoundImageView: ImageView,
            emptyListTextView: TextView,
            emptyListImageView: ImageView,
            tvError: TextView,
            ivError: ImageView,
            shimmers: ShimmerFrameLayout,
        ) {
            toolbar.visibility = View.GONE
            recyclerView.visibility = View.GONE
            nothingWasFoundTextView.visibility = View.GONE
            nothingWasFoundImageView.visibility = View.GONE
            emptyListTextView.visibility = View.GONE
            emptyListImageView.visibility = View.GONE
            tvError.visibility = View.GONE
            ivError.visibility = View.GONE
            shimmers.visibility = View.VISIBLE
            shimmers.startShimmer()
        }

    }

    data class Success(
        private val qrCodes: List<QrCodeUi> = emptyList()
    ) : HomeUiState() {
        override fun show(
            toolbar: MaterialToolbar,
            recyclerView: RecyclerView,
            adapter: QrCodesAdapter,
            nothingWasFoundTextView: TextView,
            nothingWasFoundImageView: ImageView,
            emptyListTextView: TextView,
            emptyListImageView: ImageView,
            tvError: TextView,
            ivError: ImageView,
            shimmers: ShimmerFrameLayout
        ) {
            shimmers.stopShimmer()
            shimmers.visibility = View.GONE
            toolbar.visibility = View.VISIBLE
            recyclerView.visibility = View.VISIBLE
            adapter.submitList(qrCodes)
            nothingWasFoundTextView.visibility = View.GONE
            nothingWasFoundImageView.visibility = View.GONE
            emptyListTextView.visibility = View.GONE
            emptyListImageView.visibility = View.GONE
            tvError.visibility = View.GONE
            ivError.visibility = View.GONE


        }

    }

    object Empty : HomeUiState() {
        override fun show(
            toolbar: MaterialToolbar,
            recyclerView: RecyclerView,
            adapter: QrCodesAdapter,
            nothingWasFoundTextView: TextView,
            nothingWasFoundImageView: ImageView,
            emptyListTextView: TextView,
            emptyListImageView: ImageView,
            tvError: TextView,
            ivError: ImageView,
            shimmers: ShimmerFrameLayout
        ) {
            shimmers.stopShimmer()
            shimmers.visibility = View.GONE
            toolbar.visibility = View.GONE
            recyclerView.visibility = View.GONE
            nothingWasFoundTextView.visibility = View.GONE
            nothingWasFoundImageView.visibility = View.GONE
            tvError.visibility = View.GONE
            ivError.visibility = View.GONE
            emptyListTextView.visibility = View.VISIBLE
            emptyListImageView.visibility = View.VISIBLE
        }

    }

    object NothingWasFound : HomeUiState() {
        override fun show(
            toolbar: MaterialToolbar,
            recyclerView: RecyclerView,
            adapter: QrCodesAdapter,
            nothingWasFoundTextView: TextView,
            nothingWasFoundImageView: ImageView,
            emptyListTextView: TextView,
            emptyListImageView: ImageView,
            tvError: TextView,
            ivError: ImageView,
            shimmers: ShimmerFrameLayout
        ) {
            shimmers.stopShimmer()
            shimmers.visibility = View.GONE
            toolbar.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
            nothingWasFoundTextView.visibility = View.VISIBLE
            nothingWasFoundImageView.visibility = View.VISIBLE
            tvError.visibility = View.GONE
            ivError.visibility = View.GONE
            emptyListTextView.visibility = View.GONE
            emptyListImageView.visibility = View.GONE
        }

    }

    data class Error(
        private val errorMessage: String
    ) : HomeUiState() {
        override fun show(
            toolbar: MaterialToolbar,
            recyclerView: RecyclerView,
            adapter: QrCodesAdapter,
            nothingWasFoundTextView: TextView,
            nothingWasFoundImageView: ImageView,
            emptyListTextView: TextView,
            emptyListImageView: ImageView,
            tvError: TextView,
            ivError: ImageView,
            shimmers: ShimmerFrameLayout
        ) {
            shimmers.stopShimmer()
            shimmers.visibility = View.GONE
            toolbar.visibility = View.GONE
            recyclerView.visibility = View.GONE
            nothingWasFoundTextView.visibility = View.GONE
            nothingWasFoundImageView.visibility = View.GONE
            tvError.visibility = View.VISIBLE
            tvError.text = errorMessage
            ivError.visibility = View.VISIBLE
            emptyListTextView.visibility = View.GONE
            emptyListImageView.visibility = View.GONE
        }
    }

}
