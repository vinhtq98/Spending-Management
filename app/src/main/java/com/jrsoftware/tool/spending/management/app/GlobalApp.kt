package com.jrsoftware.tool.spending.management.app

import android.annotation.SuppressLint
import android.app.Application
import com.jrsoftware.tool.spending.management.utils.SharePreferenceUtil

class GlobalApp : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: GlobalApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        SharePreferenceUtil.initializeInstance(this)
    }
}