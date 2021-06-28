package com.jorch.weatherapp.ui

import android.app.Application
import com.jorch.weatherapp.ui.utils.DelegateExt

class App :Application() {

    companion object {
        var instance: App by DelegateExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}