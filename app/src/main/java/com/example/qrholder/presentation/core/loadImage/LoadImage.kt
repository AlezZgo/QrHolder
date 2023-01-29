package com.example.qrholder.presentation.core.loadImage

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun ImageView.load(path: String) = Glide.with(this).load(path).into(this)

fun ImageView.loadWithoutCaching(path: String) = Glide
    .with(this)
    .load(path)
    .skipMemoryCache(true)
    .diskCacheStrategy(DiskCacheStrategy.NONE)
    .into(this)
