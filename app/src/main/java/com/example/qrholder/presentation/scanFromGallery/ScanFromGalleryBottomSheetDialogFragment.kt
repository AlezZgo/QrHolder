package com.example.qrholder.presentation.scanFromGallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.qrholder.databinding.FragmentScanFromGalleryBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ScanFromGalleryBottomSheetDialogFragment : BottomSheetDialogFragment(){

    private lateinit var binding : FragmentScanFromGalleryBinding

    private val viewModel by viewModels<ScanFromGalleryBottomSheetViewModel>()

    private val galleryAdapter by lazy {
        GalleryImagesAdapter(
            onImageClick = { oldImage->
                viewModel.changeSelectedState(oldImage)
            }
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentScanFromGalleryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init(true)
        viewModel.observeUiState(viewLifecycleOwner){ images ->
            galleryAdapter.submitList(images)
            binding.floatingActionButton.isVisible = images.find { it.isSelected() } !=null
        }
        binding.recyclerView.adapter = galleryAdapter


    }

}
