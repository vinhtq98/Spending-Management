package com.jrsoftware.template.ui.component.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.CountDownTimer
import com.jrsoftware.template.R
import com.jrsoftware.template.ui.base.BaseActivity
import com.jrsoftware.template.ui.component.main.MainActivity
import com.jrsoftware.template.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun getLayoutActivity(): Int = R.layout.activity_splash

    override fun initViews() {
        super.initViews()
        object : CountDownTimer(3000L,1000L){
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                Intent(this@SplashActivity, MainActivity::class.java).apply {
                    startActivity(this)
                }
                finish()
            }

        }.start()
    }
}