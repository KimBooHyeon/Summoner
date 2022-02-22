package com.opgg.summoner

import android.app.Application
import android.content.Context
import android.util.TypedValue
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

    fun convertDpToPixel(context: Context, dp: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics).toInt()
    }
}