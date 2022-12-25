package com.example.qrholder.presentation.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.qrholder.R
import com.example.qrholder.databinding.ActivityMainBinding
import com.example.qrholder.presentation.core.InitUI
import com.example.qrholder.presentation.core.fragment.BottomNavViewVisibility
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), InitUI {

    //Todo Should I clear it in onDestroy? I have already checked, there is no any leaks from there
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val navView by lazy { binding.navView }
    private val navController by lazy { findNavController(R.id.nav_host_fragment_activity_main) }
    private val handleBottomNavViewVisibility by lazy { HandleBottomNavViewVisibility.Base() }
    private val fragmentCreatedCallBack by lazy {
        object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentViewCreated(
                fm: FragmentManager,
                fragment: Fragment,
                v: View,
                savedInstanceState: Bundle?
            ) = handleBottomNavViewVisibility.show(navView, fragment)
        }
    }

    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        navView.setupWithNavController(navController)
        viewModel.init(savedInstanceState == null)
        setupViews()
        setupListeners()
        observe()
    }

    override fun setupListeners() {
        super.setupListeners()
        binding.fabBuild.setOnClickListener {
            navController.navigate(R.id.buildQrCodeFragment)
            viewModel.changeFabState(MainFabUiState.Closed)
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
