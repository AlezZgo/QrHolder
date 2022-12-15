package com.example.qrholder.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.qrholder.databinding.FragmentHomeBinding
import com.example.qrholder.presentation.core.AbstractFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : AbstractFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate,
    HomeViewModel::class.java
)