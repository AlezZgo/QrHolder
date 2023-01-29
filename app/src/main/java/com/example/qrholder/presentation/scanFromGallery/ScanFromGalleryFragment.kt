package com.example.qrholder.presentation.scanFromGallery

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qrholder.R
import com.example.qrholder.databinding.FragmentScanFromGalleryBinding
import com.example.qrholder.presentation.core.fragment.AbstractFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScanFromGalleryFragment : AbstractFragment<FragmentScanFromGalleryBinding,ScanFromGalleryViewModel>(
    FragmentScanFromGalleryBinding::inflate,ScanFromGalleryViewModel::class.java
) {



}