package com.android.bizom

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BizomApplication : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}