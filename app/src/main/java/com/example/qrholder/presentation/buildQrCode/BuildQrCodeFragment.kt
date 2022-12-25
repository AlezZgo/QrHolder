package com.example.qrholder.presentation.buildQrCode

import android.text.Editable
import androidx.core.widget.addTextChangedListener
import com.example.qrholder.databinding.FragmentBuildQrCodeBinding
import com.example.qrholder.presentation.core.SimpleTextWatcher
import com.example.qrholder.presentation.core.fragment.AbstractFragment
import com.example.qrholder.presentation.core.fragment.BottomNavViewVisibility

class BuildQrCodeFragment : AbstractFragment<FragmentBuildQrCodeBinding, BuildQrCodeViewModel>(
    FragmentBuildQrCodeBinding::inflate, BuildQrCodeViewModel::class.java
), BottomNavViewVisibility.Hide{

//    private val titleWatcher = object : SimpleTextWatcher() {
//        override fun afterTextChanged(title: Editable?) = viewModel.mapTitle(title.toString())
//    }
//    private val contentWatcher = object : SimpleTextWatcher() {
//        override fun afterTextChanged(content: Editable?) = viewModel.mapContent(content.toString())
//    }

//    override fun onResume() {
//        super.onResume()
//        binding.tielTitile.addTextChangedListener (titleWatcher)
//    }
//
//    override fun onPause() {
//        super.onPause()
//        binding.tielTitile.removeTextChangedListener(titleWatcher)
//    }
}

