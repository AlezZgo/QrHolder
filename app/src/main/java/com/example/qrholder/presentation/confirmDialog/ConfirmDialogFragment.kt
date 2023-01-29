package com.example.qrholder.presentation.confirmDialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.qrholder.R
import com.example.qrholder.databinding.DialogFragmentOnItemClickBinding
import com.example.qrholder.databinding.FragmentConfirmDialogBinding
import com.example.qrholder.presentation.home.HomeViewModel

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
                .setFragmentResult(CONFIRM_DIALOG_RESULT,Bundle().apply { putString(CONFIRM_DIALOG_RESULT,OK) })
            dismiss()
        }
        binding.btnCancel.setOnClickListener {
            requireActivity().supportFragmentManager
                .setFragmentResult(CONFIRM_DIALOG_RESULT,Bundle().apply { putString(CONFIRM_DIALOG_RESULT,CANCEL) })
            dismiss()
        }
    }
    companion object{
        const val CONFIRM_DIALOG_RESULT = "confirm_dialog_result"
        const val OK = "ok"
        private const val CANCEL = "cancel"
    }
}