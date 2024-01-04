package com.example.billz.ui.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.utils.Constants
import com.example.ViewModel.UserViewModel
import com.example.billz.R
import com.example.billz.home.MainActivity

class SplashScreen:  AppCompatActivity(){
        private val handler = Handler(Looper.getMainLooper())
        val userViewModel: UserViewModel by viewModels()
        lateinit var sharedPrefs: SharedPreferences
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_splash)
            sharedPrefs = getSharedPreferences(Constants.PREFS_FILE, Context.MODE_PRIVATE)
            handler.postDelayed({
                redirectToAppropriateScreen()

            }, 2000)

        }

    private fun redirectToAppropriateScreen () {
        val isLoggedIn = sharedPrefs.getBoolean("IS_LOGGED_IN", false)

            if (isLoggedIn) {
                startActivity(Intent(baseContext, MainActivity::class.java))
            }
        else{
            startActivity(Intent(baseContext, LoginPage::class.java))
        }
        finish()
    }
        }
