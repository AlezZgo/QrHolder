package com.alezzgo.qrholder.data.cache

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface SharedPrefs {
    fun savePermissionNeverShow(neverShow: Boolean)
    fun fetchPermissionNeverShow(): Boolean

    class Base @Inject constructor(
        @ApplicationContext private val context: Context
    ) : SharedPrefs {

        private val sharedPrefs: SharedPreferences = context.getSharedPreferences(
            APP_PREF, Context.MODE_PRIVATE
        )

        override fun savePermissionNeverShow(neverShow: Boolean) =
            sharedPrefs.edit().putBoolean(SYSTEM_SETTINGS_NEVER_SHOW, neverShow).apply()

        override fun fetchPermissionNeverShow(): Boolean =
            sharedPrefs.getBoolean(SYSTEM_SETTINGS_NEVER_SHOW, false)

        companion object {
            private const val APP_PREF = "APP_PREF"
            const val SYSTEM_SETTINGS_NEVER_SHOW = "system_settings_never_show"
        }


    }
}
