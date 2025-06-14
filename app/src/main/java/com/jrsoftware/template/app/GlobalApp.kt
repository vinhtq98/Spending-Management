package com.jrsoftware.template.app

import android.annotation.SuppressLint
import android.app.Application
import com.jrsoftware.template.utils.SharePreferenceUtil

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