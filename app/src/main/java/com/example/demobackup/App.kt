package com.example.demobackup

import android.app.Application
import com.example.demobackup.utils.PrefKtx

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        PrefKtx.initializePref(this@App)
    }
}