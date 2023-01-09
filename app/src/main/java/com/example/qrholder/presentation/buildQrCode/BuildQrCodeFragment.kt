package com.example.qrholder.presentation.buildQrCode

import android.text.Editable
import android.widget.Toast
import com.example.qrholder.databinding.FragmentBuildQrCodeBinding
import com.example.qrholder.domain.QrCodeBuildResult
import com.example.qrholder.presentation.core.SimpleTextWatcher
import com.example.qrholder.presentation.core.fragment.AbstractFragment
import com.example.qrholder.presentation.core.fragment.BottomNavViewVisibility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuildQrCodeFragment : AbstractFragment<FragmentBuildQrCodeBinding, BuildQrCodeViewModel>(
    FragmentBuildQrCodeBinding::inflate, BuildQrCodeViewModel::class.java
), BottomNavViewVisibility.Hide{

    private val titleTextChangedListener = object : SimpleTextWatcher() {
        override fun afterTextChanged(title: Editable?) = viewModel.changeTitle(title.toString())
    }
    private val contentTextChangedListener = object : SimpleTextWatcher() {
        override fun afterTextChanged(content: Editable?) = viewModel.changeContent(content.toString())
    }

    override fun observe() {
        super.observe()

        viewModel.observeTitleUiState(viewLifecycleOwner){ titleUiState ->
            titleUiState.show(binding.tielTitle)
        }
        viewModel.observeContentUiState(viewLifecycleOwner){ contentUiState ->
            contentUiState.show(binding.tielContent)
        }

        viewModel.observeBuildResultState(viewLifecycleOwner){ buildResult ->
            buildResult.show(binding.ivPreview) {
                if (buildResult is QrCodeBuildResult.Success){
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                }else if (buildResult is QrCodeBuildResult.Error){
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, "fuck", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }

    override fun setupListeners() {
        super.setupListeners()
        binding.btnBuild.setOnClickListener { viewModel.build() }
    }

    override fun onResume() {
        super.onResume()
        binding.tielTitle.addTextChangedListener (titleTextChangedListener)
        binding.tielContent.addTextChangedListener (contentTextChangedListener)
    }

    override fun onPause() {
        super.onPause()
        binding.tielTitle.removeTextChangedListener(titleTextChangedListener)
        binding.tielContent.removeTextChangedListener(contentTextChangedListener)
    }
}

