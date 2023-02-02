package com.example.qrholder.presentation.editQrCode

import android.text.Editable
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.qrholder.R
import com.example.qrholder.databinding.FragmentEditQrCodeBinding
import com.example.qrholder.presentation.buildQrCode.BuildQrCodeFragmentDirections
import com.example.qrholder.presentation.core.SimpleTextWatcher
import com.example.qrholder.presentation.core.fragment.AbstractFragment
import com.example.qrholder.presentation.core.fragment.BottomNavViewVisibility
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditQrCodeFragment : AbstractFragment<FragmentEditQrCodeBinding, EditQrCodeVIewModel>(
    FragmentEditQrCodeBinding::inflate, EditQrCodeVIewModel::class.java
), BottomNavViewVisibility.Hide  {

    private val args by navArgs<EditQrCodeFragmentArgs>()

    private val contentTextChangedListener = object : SimpleTextWatcher() {
        override fun afterTextChanged(content: Editable?) =
            viewModel.changeContent(content.toString())
    }

    override fun setupViews() {
        super.setupViews()
        args.qrCode.run {
            loadTitle(binding.tielTitle)
            loadContent(binding.tielContent)
        }
    }

    override fun observe() {
        super.observe()

        viewModel.observeBuildResultState(viewLifecycleOwner) { buildResult ->
            buildResult.show(
                //todo move to another fragment
                successBuildAction = { qrCode ->
                    findNavController().popBackStack()
                },
                errorBuildAction = { errorMessage ->
                    MaterialAlertDialogBuilder(
                        requireContext(),
                    ).setTitle(getString(R.string.error))
                        .setMessage(errorMessage)
                        .show()
                }
            )

        }
        viewModel.observeContent(viewLifecycleOwner) { validationResult ->
            validationResult.show(binding.tielContent)
        }

    }

    override fun setupListeners() {
        super.setupListeners()
        binding.btnBuild.setOnClickListener { args.qrCode.edit(viewModel) }
    }

    override fun onResume() {
        super.onResume()
        binding.tielContent.addTextChangedListener(contentTextChangedListener)
    }

    override fun onPause() {
        super.onPause()
        binding.tielContent.removeTextChangedListener(contentTextChangedListener)
    }
}