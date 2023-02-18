package com.alezzgo.qrholder.presentation.home

import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import com.alezzgo.qrholder.R
import com.alezzgo.qrholder.core.ManageResources
import com.alezzgo.qrholder.databinding.FragmentHomeBinding
import com.alezzgo.qrholder.presentation.core.SimpleOnQueryTextListener
import com.alezzgo.qrholder.presentation.core.fragment.AbstractFragment
import com.alezzgo.qrholder.presentation.core.fragment.BottomNavViewVisibility
import com.alezzgo.qrholder.presentation.home.adapter.QrCodesAdapter
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
                findNavController().navigate(
                    HomeFragmentDirections.actionNavigationHomeToDescriptionFragment(
                        qrCode
                    )
                )
            },
            onCardLongClick = { qrCode ->
                findNavController().navigate(
                    HomeFragmentDirections.actionNavigationHomeToOnItemClickDialogFragment(
                        qrCode
                    )
                )
            },
            onImageClick = { qrCode ->
                findNavController().navigate(
                    HomeFragmentDirections.actionNavigationHomeToQrCodeImageDialogFragment(
                        qrCode
                    )
                )
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
            uiState.show(
                adapter = adapter,
                binding = binding
            )
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