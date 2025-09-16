package com.am0520.amphibians

import android.app.Application
import com.am0520.amphibians.data.AppContainer
import com.am0520.amphibians.data.DefaultAppContainer

class AmphibiansApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
