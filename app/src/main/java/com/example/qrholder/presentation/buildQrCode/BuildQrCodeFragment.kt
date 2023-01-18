package com.example.qrholder.presentation.buildQrCode

import android.text.Editable
import androidx.navigation.fragment.findNavController
import com.example.qrholder.R
import com.example.qrholder.core.HideKeyBoard
import com.example.qrholder.databinding.FragmentBuildQrCodeBinding
import com.example.qrholder.presentation.core.SimpleTextWatcher
import com.example.qrholder.presentation.core.fragment.AbstractFragment
import com.example.qrholder.presentation.core.fragment.BottomNavViewVisibility
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BuildQrCodeFragment : AbstractFragment<FragmentBuildQrCodeBinding, BuildQrCodeViewModel>(
    FragmentBuildQrCodeBinding::inflate, BuildQrCodeViewModel::class.java
), BottomNavViewVisibility.Hide {

    @Inject
    lateinit var hideKeyBoard: HideKeyBoard

    private val titleTextChangedListener = object : SimpleTextWatcher() {
        override fun afterTextChanged(title: Editable?) = viewModel.changeTitle(title.toString())
    }
    private val contentTextChangedListener = object : SimpleTextWatcher() {
        override fun afterTextChanged(content: Editable?) =
            viewModel.changeContent(content.toString())
    }

    override fun observe() {
        super.observe()

        viewModel.observeBuildResultState(viewLifecycleOwner) { buildResult ->
            buildResult.show(
                //todo move to another fragment
                successBuildAction = { qrCode ->
                    findNavController().navigate(BuildQrCodeFragmentDirections.actionBuildQrCodeFragmentToSuccessfullyBuiltFragment())
                },
                errorBuildAction = { errorMessage ->
                    MaterialAlertDialogBuilder(
                        requireContext(),
                        R.style.Body_ThemeOverlay_MaterialComponents_MaterialAlertDialog
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

