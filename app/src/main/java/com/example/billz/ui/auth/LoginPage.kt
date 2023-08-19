package com.example.billz.ui.auth

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.billz.databinding.ActivityLoginBinding

class LoginPage:  AppCompatActivity(){
    private val handler = Handler(Looper.getMainLooper())
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_login)


    }

}