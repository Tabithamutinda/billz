package com.example.billz.ui.auth

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.billz.ui.home.MainActivity
import com.example.billz.R

class SplashScreen:  AppCompatActivity(){
        private val handler = Handler(Looper.getMainLooper())
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_splash)

            handler.postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, 2000)

        }

    }