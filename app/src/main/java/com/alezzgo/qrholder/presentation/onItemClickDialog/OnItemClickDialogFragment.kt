package com.alezzgo.qrholder.presentation.onItemClickDialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alezzgo.qrholder.R
import com.alezzgo.qrholder.databinding.DialogFragmentOnItemClickBinding
import com.alezzgo.qrholder.presentation.confirmDialog.ConfirmDialogFragment
import com.alezzgo.qrholder.presentation.home.HomeViewModel


class OnItemClickDialogFragment : DialogFragment() {

    private val viewModel by activityViewModels<HomeViewModel>()

    private lateinit var binding: DialogFragmentOnItemClickBinding

    private val args by navArgs<OnItemClickDialogFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogFragmentOnItemClickBinding.inflate(inflater)
        binding.run {

            requireActivity().supportFragmentManager
                .setFragmentResultListener(
                    ConfirmDialogFragment.CONFIRM_DIALOG_RESULT,
                    viewLifecycleOwner
                ) { _, bundle ->
                    if (bundle.getString(ConfirmDialogFragment.CONFIRM_DIALOG_RESULT) == ConfirmDialogFragment.OK) {
                        viewModel.delete(args.qrCode)
                        dismiss()
                    } else {
                        dismiss()
                    }
                }

            share.setOnClickListener {
                args.qrCode.share(binding.root.context)
                dismiss()
            }
            browse.setOnClickListener {
                args.qrCode.browse(binding.root.context)
                dismiss()
            }
            delete.setOnClickListener {
                findNavController().navigate(
                    OnItemClickDialogFragmentDirections.actionOnItemClickDialogFragmentToConfirmDialogFragment(
                        getString(R.string.sure_delete_qr_code)
                    )
                )
            }
            edit.setOnClickListener {
                findNavController().navigate(
                    OnItemClickDialogFragmentDirections.actionOnItemClickDialogFragmentToEditQrCodeFragment(
                        args.qrCode
                    )
                )
                dismiss()
            }

        }
        return binding.root
    }

}