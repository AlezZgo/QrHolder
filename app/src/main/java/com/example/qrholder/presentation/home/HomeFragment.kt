package com.example.qrholder.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.qrholder.databinding.FragmentHomeBinding
import com.example.qrholder.presentation.core.AbstractFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : AbstractFragment<FragmentHomeBinding,HomeViewModel>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init(savedInstanceState==null)
    }


}