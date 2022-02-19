package com.opgg.summoner.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Global: Application() {
    companion object {
        lateinit var INSTANCE: Global
    }
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}