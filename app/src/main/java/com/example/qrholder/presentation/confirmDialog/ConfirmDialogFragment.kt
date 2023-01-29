package com.example.qrholder.presentation.confirmDialog

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.qrholder.R
import com.example.qrholder.databinding.DialogFragmentOnItemClickBinding
import com.example.qrholder.databinding.FragmentConfirmDialogBinding
import com.example.qrholder.presentation.home.HomeViewModel

class ConfirmDialogFragment : DialogFragment() {

    private val viewModel by activityViewModels<HomeViewModel>()

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
        args.confirmDialogInitState.show(binding.tvTitle,binding.btnOk,binding.btnCancel){
            dismiss()
        }

    }
}