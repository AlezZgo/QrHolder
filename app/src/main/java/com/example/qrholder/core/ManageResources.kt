package com.example.qrholder.core

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

interface ManageResources {

    fun string(@StringRes id: Int): String

    class Base @Inject constructor(private val context: Context) : ManageResources {
        override fun string(id: Int) = context.getString(id)
    }

}
