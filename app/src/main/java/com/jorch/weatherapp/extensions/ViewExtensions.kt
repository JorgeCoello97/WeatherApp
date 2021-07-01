package com.jorch.weatherapp.extensions

import android.content.Context
import android.view.TextureView
import android.view.View
import android.widget.TextView

val View.ctx: Context
    get() = context

var TextView.textColor: Int
    set(value) = setTextColor(value)
    get() = currentTextColor