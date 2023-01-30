package com.example.qrholder.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.qrholder.R
import com.example.qrholder.databinding.ActivityMainBinding
import com.example.qrholder.presentation.core.InitUI
import com.example.qrholder.presentation.core.fragment.BottomNavViewVisibility
import com.example.qrholder.presentation.home.HomeFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.zxing.integration.android.IntentIntegrator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), InitUI {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navView: BottomNavigationView
    private lateinit var navController: NavController
    private val handleBottomNavViewVisibility by lazy { HandleBottomNavViewVisibility.Base() }
    private val fragmentCreatedCallBack = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentStarted(fm: FragmentManager, fragment: Fragment) {
            super.onFragmentStarted(fm, fragment)
            handleBottomNavViewVisibility.show(navView, fragment)
            if(fragment is BottomNavViewVisibility.Hide){
                binding.fabBuild.isGone = true
                binding.fabScan.isGone = true
                binding.fabGallery.isGone = true
            }
        }
    }

    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)
        viewModel.init(savedInstanceState == null)

        setupViews()
        setupListeners()
        observe()
    }

    override fun setupListeners() {
        super.setupListeners()
        binding.fabBuild.setOnClickListener {
            navController.navigate(HomeFragmentDirections.actionNavigationHomeToBuildQrCodeFragment())
            viewModel.changeFabState(MainFabUiState.Closed)
        }
        binding.fabScan.setOnClickListener {
            IntentIntegrator(this).apply {
                setOrientationLocked(false)
                setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
                setPrompt("Scan a qr code")
                setCameraId(0)
                setBeepEnabled(false)
                initiateScan()
            }
            viewModel.changeFabState(MainFabUiState.Closed)
        }
        binding.fabGallery.setOnClickListener {
            navController.navigate(HomeFragmentDirections.actionNavigationHomeToScanFromGalleryBottomSheetDialogFragment())
        }
        viewModel.observeQrCodeScanned(this) {
            navController.navigate(
                R.id.buildQrCodeFragment,
                Bundle().apply { putString("content", it) })
        }
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentCreatedCallBack, true)
    }

    override fun observe() {
        super.observe()
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
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result?.contents != null) {
            viewModel.changeQrCodeScanned(result.contents)
        }

    }

}

interface HandleBottomNavViewVisibility {

    fun show(navView: BottomNavigationView, fragment: Fragment)

    class Base : HandleBottomNavViewVisibility {
        override fun show(navView: BottomNavigationView, fragment: Fragment) {
            navView.visibility = when (fragment) {
                is BottomNavViewVisibility.Show -> View.VISIBLE
                is BottomNavViewVisibility.Hide -> View.GONE
                else -> navView.visibility
            }
        }
    }
}