package com.example.qrholder.presentation.home

import android.widget.SearchView
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.qrholder.R
import com.example.qrholder.core.ManageResources
import com.example.qrholder.databinding.FragmentHomeBinding
import com.example.qrholder.presentation.core.SimpleOnQueryTextListener
import com.example.qrholder.presentation.core.fragment.AbstractFragment
import com.example.qrholder.presentation.core.fragment.BottomNavViewVisibility
import com.example.qrholder.presentation.home.adapter.QrCodesAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : AbstractFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate,
    HomeViewModel::class.java
), BottomNavViewVisibility.Show {

    @Inject
    lateinit var manageResources: ManageResources

    private lateinit var searchView: SearchView

    private val textChangedListener by lazy {
        SimpleOnQueryTextListener { searchText ->
            viewModel.filter(searchText)
        }
    }

    private val adapter by lazy {
        QrCodesAdapter(
            onCardClick = { qrCode ->

            },
            onCardLongClick = { qrCode ->
                findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToOnItemClickDialogFragment(qrCode))
            },
            onImageClick = { qrCode ->
                findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToQrCodeImageDialogFragment(qrCode))
            }
        )
    }

    override fun setupViews() {
        super.setupViews()
        isSharedViewModel = true
        with(binding) {
            rvQrList.adapter = adapter
            searchView = toolbar.menu.findItem(R.id.appBarSearch).actionView as SearchView
            searchView.queryHint = manageResources.string(R.string.search_query_hint)
        }
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

    override fun onResume() {
        super.onResume()
        searchView.setOnQueryTextListener(textChangedListener)
        binding.toolbar.requestFocus()
        if (searchView.query.isEmpty())
            searchView.onActionViewCollapsed()
    }

    override fun onPause() {
        super.onPause()
        searchView.setOnQueryTextListener(null)
    }
}