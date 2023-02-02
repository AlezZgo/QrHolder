package com.example.qrholder.core

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import javax.inject.Inject


interface HideKeyBoard {

    fun hideKeyboardFrom(context: Context, view: View)

    class Base @Inject constructor() : HideKeyBoard{

        override fun hideKeyboardFrom(context: Context, view: View) {
            val imm: InputMethodManager =
                context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
        }

    }
}