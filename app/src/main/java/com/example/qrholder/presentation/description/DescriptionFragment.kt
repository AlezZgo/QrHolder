package com.example.qrholder.presentation.description

import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.lifecycle.withResumed
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.qrholder.R
import com.example.qrholder.databinding.FragmentDescriptionBinding
import com.example.qrholder.presentation.confirmDialog.ConfirmDialogFragment
import com.example.qrholder.presentation.core.fragment.AbstractFragment
import com.example.qrholder.presentation.core.fragment.BottomNavViewVisibility
import com.example.qrholder.presentation.home.HomeFragment
import com.example.qrholder.presentation.home.HomeViewModel
import com.example.qrholder.presentation.onItemClickDialog.OnItemClickDialogFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DescriptionFragment : AbstractFragment<FragmentDescriptionBinding, HomeViewModel>(
    FragmentDescriptionBinding::inflate, HomeViewModel::class.java
), BottomNavViewVisibility.Hide  {

    private val args by navArgs<DescriptionFragmentArgs>()

    override fun setupViews() {
        super.setupViews()

        lifecycleScope.launchWhenStarted{
            val newQrCode = args.qrCode.reload(viewModel)
            newQrCode.run {
                loadImage(binding.ivQrCode)
                loadTitle(binding.tvTitle)
                loadContent(binding.tvContent)
            }

            requireActivity().supportFragmentManager
                .setFragmentResultListener(ConfirmDialogFragment.CONFIRM_DIALOG_RESULT, viewLifecycleOwner) { _, bundle ->
                    if(bundle.getString(ConfirmDialogFragment.CONFIRM_DIALOG_RESULT) == ConfirmDialogFragment.OK){
                        viewModel.delete(newQrCode)
                        findNavController().navigateUp()
                        findNavController().navigateUp()
                    }
                }

            binding.fabShare.setOnClickListener {
                newQrCode.share(binding.root.context)
            }

            binding.fabBrowse.setOnClickListener {
                newQrCode.browse(binding.root.context)
            }
            binding.fabDelete.setOnClickListener {
                findNavController().navigate(
                    DescriptionFragmentDirections.actionDescriptionFragmentToConfirmDialogFragment(
                        getString(R.string.sure_delete_qr_code)
                    )
                )
            }
            binding.fabEdit.setOnClickListener {
                findNavController().navigate(
                    DescriptionFragmentDirections.actionDescriptionFragmentToEditQrCodeFragment(
                        newQrCode
                    )
                )
            }
            binding.ivQrCode.setOnClickListener {
                findNavController().navigate(
                    DescriptionFragmentDirections.actionDescriptionFragmentToQrCodeImageDialogFragment(
                        newQrCode
                    )
                )
            }
        }


    }

}