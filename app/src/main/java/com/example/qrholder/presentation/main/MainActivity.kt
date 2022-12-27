package com.example.qrholder.presentation.main

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
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

    private lateinit var binding: ActivityMainBinding
    private lateinit var navView: BottomNavigationView
    private lateinit var navController: NavController
    private val handleBottomNavViewVisibility by lazy { HandleBottomNavViewVisibility.Base() }
    private val fragmentCreatedCallBack = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            fragment: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) = handleBottomNavViewVisibility.show(navView, fragment)
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

    fun hideKeyboard(clearAction : ()->Unit) {
        try {
            val inputManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as (InputMethodManager);
            val currentFocusedView = currentFocus;
            if (currentFocusedView != null) {
                inputManager.hideSoftInputFromWindow(
                    currentFocusedView.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                );
                clearAction.invoke()
            }
            clearAction.invoke()
        } catch (e: Exception) {
            e.printStackTrace();
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