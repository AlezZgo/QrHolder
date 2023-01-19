package com.example.qrholder.presentation.core.loadImage

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.load(path: String) = Glide.with(this).load(path).into(this)
