package com.example.goodmorningapp.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.goodmorningapp.R

fun ImageView.getImage(url: String) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.ic_baseline_wb_sunny_24)
        .timeout(10_000)
        .into(this)
}