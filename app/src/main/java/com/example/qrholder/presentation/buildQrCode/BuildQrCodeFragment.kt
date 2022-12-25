package com.example.qrholder.presentation.buildQrCode

import com.example.qrholder.databinding.FragmentBuildQrCodeBinding
import com.example.qrholder.presentation.core.fragment.AbstractFragment
import com.example.qrholder.presentation.core.fragment.BottomNavViewVisibility

class BuildQrCodeFragment : AbstractFragment<FragmentBuildQrCodeBinding, BuildQrCodeViewModel>(
    FragmentBuildQrCodeBinding::inflate, BuildQrCodeViewModel::class.java
), BottomNavViewVisibility.Hide