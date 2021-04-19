package com.example.calculator.view.splash

import android.os.Handler
import android.os.Looper
import com.example.calculator.R
import com.example.calculator.base.BaseActivity
import com.example.calculator.base.MainActivity

private const val TIME = 2000L

class SplashActivity : BaseActivity(R.layout.activity_splash) {
    override fun onCreatedView() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(MainActivity.getIntent(this))
            finish()
        }, TIME)
    }
}
