package com.example.qrholder.presentation.main


import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.Nullable
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.qrholder.R
import com.example.qrholder.databinding.ActivityMainBinding
import com.example.qrholder.presentation.core.InitUI
import com.example.qrholder.presentation.core.fragment.BottomNavViewVisibility
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.zxing.integration.android.IntentIntegrator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.mintrocket.lib.mintpermissions.MintPermissionsController
import ru.mintrocket.lib.mintpermissions.ext.isDenied
import ru.mintrocket.lib.mintpermissions.ext.isGranted
import ru.mintrocket.lib.mintpermissions.ext.isNeedsRationale
import javax.inject.Inject


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
            if (fragment is BottomNavViewVisibility.Hide) {
                binding.fabBuild.isGone = true
                binding.fabScan.isGone = true
                binding.fabGallery.isGone = true
            }
        }
    }

    @Inject
    lateinit var permissionsController: MintPermissionsController

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


            //todo fix
            lifecycleScope.launch {

                val permission = if (Build.VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU) {
                    READ_MEDIA_IMAGES
                } else {
                    READ_EXTERNAL_STORAGE
                }

                val result = permissionsController.request(permission)

                when {
                    result.isGranted() -> {
                        navController.navigate(R.id.scanFromGalleryBottomSheetDialogFragment)
                    }
                    result.isDenied() -> {
                        val appSettingsIntent = Intent(
                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.fromParts("package", packageName, null)

                        )

                        AlertDialog.Builder(this@MainActivity)
                            .setTitle(getString(R.string.permission_denied))
                            .setMessage(getString(R.string.permission_denied_forever_message))
                            .setPositiveButton(getString(R.string.ok)) { _, _ ->
                                lifecycleScope.launch {
                                    startActivity(appSettingsIntent)
                                }
                            }.setNegativeButton(getString(R.string.no_thanks)) { dialog, _ ->
                                dialog.dismiss()
                            }.show()


                    }
                    result.isNeedsRationale() -> {
                        AlertDialog.Builder(this@MainActivity)
                            .setTitle(getString(R.string.enable_read_images_from_gallery_title))
                            .setMessage(getString(R.string.enable_read_images_from_gallery_content))
                            .setPositiveButton(getString(R.string.ok)) { _, _ ->
                                lifecycleScope.launch {
                                    permissionsController.request(permission)
                                }
                            }.show()
                    }
                }
            }

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