package com.cropbit

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CropBitApplication(): Application() {

    override fun onCreate() {
        super.onCreate()
    }

}