package com.example.qrholder.home.data.cache.db.core

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId

abstract class Page {
    protected fun Int.view() = onView(withId(this))
}