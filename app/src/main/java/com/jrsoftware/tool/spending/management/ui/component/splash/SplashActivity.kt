package com.jrsoftware.tool.spending.management.ui.component.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.CountDownTimer
import com.jrsoftware.tool.spending.management.R
import com.jrsoftware.tool.spending.management.databinding.ActivitySplashBinding
import com.jrsoftware.tool.spending.management.ui.component.main.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : com.jrsoftware.tool.spending.management.ui.base.BaseActivity<ActivitySplashBinding>() {

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