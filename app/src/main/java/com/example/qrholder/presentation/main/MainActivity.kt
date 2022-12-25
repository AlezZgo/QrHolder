package com.example.qrholder.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.qrholder.R
import com.example.qrholder.databinding.ActivityMainBinding
import com.example.qrholder.presentation.buildQrCode.BuildQrCodeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //Todo Should I clear it in onDestroy? I have already checked, there is no any leaks from there
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val navView by lazy { binding.navView }
    private val navController by lazy { findNavController(R.id.nav_host_fragment_activity_main) }

    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        navView.setupWithNavController(navController)

        viewModel.init(savedInstanceState == null)

        viewModel.observeUiState(this) { fabUiState ->
            fabUiState.show(
                binding.fabParent,
                binding.fabBuild,
                binding.fabGallery,
                binding.fabScan,
                onParentFabClick = {
                    viewModel.changeFabState(fabUiState.opposite())
                }
            )
        }

        binding.fabBuild.setOnClickListener {
            //todo fix navigation, (now in develop)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main,BuildQrCodeFragment())
                .addToBackStack(null)
                .commit()
            viewModel.changeFabState(MainFabUiState.Closed)
        }

    }
}