package com.alezzgo.qrholder.presentation.core

import android.content.Context
import android.provider.Settings
import android.util.Log
import android.view.Window
import android.view.WindowManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


interface ManageBrightness {

    fun changeBrightness(window: Window, brightnessLevel: Int)

    fun changeBrightnessMode(mode: Int)

    fun currentValue(): Int

    class Base @Inject constructor(
        @ApplicationContext private val context: Context
    ) : ManageBrightness {

        override fun changeBrightness(window: Window, brightnessLevel: Int) {


            val cResolver = context.contentResolver;

            try {
                Settings.System.putInt(
                    cResolver, Settings.System.SCREEN_BRIGHTNESS, brightnessLevel
                )

                val layout: WindowManager.LayoutParams = window.attributes
                layout.screenBrightness = 255f
                window.attributes = layout

            } catch (e: Settings.SettingNotFoundException) {
                Log.e("Error", "Cannot access system brightness");
                e.printStackTrace()
            }
        }

        override fun changeBrightnessMode(mode: Int) {
            Settings.System.putInt(
                context.contentResolver,
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                mode
            )
        }

        override fun currentValue(): Int = Settings.System.getInt(
            context.contentResolver, Settings.System.SCREEN_BRIGHTNESS
        )

    }

}
