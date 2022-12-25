package com.example.qrholder.presentation.home

import android.widget.SearchView
import com.example.qrholder.R
import com.example.qrholder.core.ManageResources
import com.example.qrholder.databinding.FragmentHomeBinding
import com.example.qrholder.presentation.core.AbstractFragment
import com.example.qrholder.presentation.core.SimpleOnQueryTextListener
import com.example.qrholder.presentation.home.adapter.QrCodesAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : AbstractFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate,
    HomeViewModel::class.java
) {

    @Inject
    lateinit var manageResources: ManageResources

    private val searchView by lazy {
        binding.toolbar.menu.findItem(R.id.app_bar_search).actionView as SearchView
    }

    private val adapter by lazy {
        QrCodesAdapter(
            onCardClick = { qrCode ->
                //Todo
            },
            onCardLongClick = { qrCode ->
                //Todo
            },
            onImageClick = { qrCode ->
                //Todo
            }
        )
    }

    override fun setupViews() {
        super.setupViews()
        with(binding) {
            rvQrList.adapter = adapter
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

    override fun setupListeners() {
        super.setupListeners()
        //Todo Is it necessary to remove Listener in onPause?
        searchView.setOnQueryTextListener(SimpleOnQueryTextListener { searchText ->
            viewModel.filter(searchText)
            }
        )
    }
}