package com.example.qrholder.presentation.qrCodeImageDialog

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS
import android.provider.Settings.System.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.qrholder.R
import com.example.qrholder.databinding.FragmentQrCodeImageDialogBinding
import com.example.qrholder.presentation.core.ManageBrightness
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class QrCodeImageDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentQrCodeImageDialogBinding

    private val args by navArgs<QrCodeImageDialogFragmentArgs>()

    private val viewModel by viewModels<QrCodeImageDialogViewModel>()

    @Inject
    lateinit var manageBrightness: ManageBrightness


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQrCodeImageDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            args.qrCode.run {
                loadImage(ivQrCode)
                loadTitle(tvTitle)
            }
        }

    }

    override fun onStart() {
        super.onStart()
        if (!canWrite(activity) && !viewModel.fetchSystemSettingsNeverShow()) {

            AlertDialog.Builder(context!!)
                .setTitle(getString(R.string.enable_changing_system_settings_title))
                .setMessage(getString(R.string.enable_changing_system_settings_content))
                .setPositiveButton(getString(R.string.ok)) { _, _ ->
                    startActivity(Intent(ACTION_MANAGE_WRITE_SETTINGS).apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) })
                }.setNeutralButton(getString(R.string.never_show_it_again)) { _, _ ->
                    viewModel.saveSystemSettingsNeverShow(neverShow = true)
                }.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                    dialog.dismiss()
                }.show()
        }
    }

    override fun onResume() {
        super.onResume()

        if (canWrite(activity)) {
            manageBrightness.run {
                changeBrightnessMode(SCREEN_BRIGHTNESS_MODE_MANUAL)
                changeBrightness(activity!!.window, 2000)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if (canWrite(activity)) {
            manageBrightness.changeBrightnessMode(SCREEN_BRIGHTNESS_MODE_AUTOMATIC)
        }

    }

}