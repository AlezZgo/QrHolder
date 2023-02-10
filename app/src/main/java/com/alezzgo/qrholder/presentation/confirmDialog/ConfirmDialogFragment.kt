package com.alezzgo.qrholder.presentation.confirmDialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.alezzgo.qrholder.databinding.FragmentConfirmDialogBinding

class ConfirmDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentConfirmDialogBinding

    private val args by navArgs<ConfirmDialogFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfirmDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvTitle.text = args.title
        binding.btnOk.setOnClickListener {

            requireActivity().supportFragmentManager
                .setFragmentResult(
                    CONFIRM_DIALOG_RESULT,
                    Bundle().apply { putString(CONFIRM_DIALOG_RESULT, OK) })
            dismiss()
        }
        binding.btnCancel.setOnClickListener {
            requireActivity().supportFragmentManager
                .setFragmentResult(
                    CONFIRM_DIALOG_RESULT,
                    Bundle().apply { putString(CONFIRM_DIALOG_RESULT, CANCEL) })
            dismiss()
        }
    }

    companion object {
        const val CONFIRM_DIALOG_RESULT = "confirm_dialog_result"
        const val OK = "ok"
        private const val CANCEL = "cancel"
    }
}