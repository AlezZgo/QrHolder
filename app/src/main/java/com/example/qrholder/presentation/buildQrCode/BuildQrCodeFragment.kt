package com.example.qrholder.presentation.buildQrCode

import android.text.Editable
import androidx.navigation.fragment.findNavController
import com.example.qrholder.R
import com.example.qrholder.databinding.FragmentBuildQrCodeBinding
import com.example.qrholder.presentation.core.SimpleTextWatcher
import com.example.qrholder.presentation.core.fragment.AbstractFragment
import com.example.qrholder.presentation.core.fragment.BottomNavViewVisibility
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuildQrCodeFragment : AbstractFragment<FragmentBuildQrCodeBinding, BuildQrCodeViewModel>(
    FragmentBuildQrCodeBinding::inflate, BuildQrCodeViewModel::class.java
), BottomNavViewVisibility.Hide {


    private val titleTextChangedListener = object : SimpleTextWatcher() {
        override fun afterTextChanged(s: Editable?) = viewModel.changeTitle(s.toString())
    }
    private val contentTextChangedListener = object : SimpleTextWatcher() {
        override fun afterTextChanged(s: Editable?) =
            viewModel.changeContent(s.toString())
    }

    override fun observe() {
        super.observe()

        viewModel.observeBuildResultState(viewLifecycleOwner) { buildResult ->
            buildResult.show(
                //todo move to another fragment
                successBuildAction = { qrCode ->
                    findNavController().navigate(
                        BuildQrCodeFragmentDirections.actionBuildQrCodeFragmentToSuccessfullyBuiltFragment(
                            qrCode
                        )
                    )
                },
                errorBuildAction = { errorMessage ->
                    MaterialAlertDialogBuilder(
                        requireContext(),
                    )
                        .setTitle(getString(R.string.error))
                        .setMessage(errorMessage)
                        .show()
                }
            )

        }

        viewModel.observeTitle(viewLifecycleOwner) { validationResult ->
            validationResult.show(binding.tielTitle)
        }

        viewModel.observeContent(viewLifecycleOwner) { validationResult ->
            validationResult.show(binding.tielContent)
        }

    }

    override fun setupListeners() {
        super.setupListeners()
        binding.btnBuild.setOnClickListener { viewModel.build() }
    }

    override fun onResume() {
        super.onResume()
        binding.tielTitle.addTextChangedListener(titleTextChangedListener)
        binding.tielContent.addTextChangedListener(contentTextChangedListener)
    }

    override fun onPause() {
        super.onPause()
        binding.tielTitle.removeTextChangedListener(titleTextChangedListener)
        binding.tielContent.removeTextChangedListener(contentTextChangedListener)
    }
}

