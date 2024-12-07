package com.oneasad.amphibians.ui

import android.app.Application
import com.oneasad.amphibians.data.AppContainer
import com.oneasad.amphibians.data.DefaultAppContainer

class AmphibiansApplication: Application()  {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}