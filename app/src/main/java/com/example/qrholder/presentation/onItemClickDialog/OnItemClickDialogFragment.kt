package com.example.qrholder.presentation.onItemClickDialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.qrholder.R
import com.example.qrholder.databinding.DialogFragmentOnItemClickBinding
import com.example.qrholder.presentation.confirmDialog.ConfirmDialogInitState
import com.example.qrholder.presentation.home.HomeViewModel


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

            share.setOnClickListener {
                args.qrCode.share(binding.root.context)
                dismiss()
            }
            browse.setOnClickListener {
                args.qrCode.browse(binding.root.context)
                dismiss()
            }
            delete.setOnClickListener {
                findNavController().navigate(OnItemClickDialogFragmentDirections.actionOnItemClickDialogFragmentToConfirmDialogFragment(
                    ConfirmDialogInitState(
                        title = getString(R.string.sure_delete_qr_code),
                        positiveAction = {
                            viewModel.delete(args.qrCode)
                        },
                        negativeAction = {}
                    )
                ))
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