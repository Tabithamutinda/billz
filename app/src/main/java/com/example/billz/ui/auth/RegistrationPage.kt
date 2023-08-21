package com.example.billz.ui.auth

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.billz.R
import com.example.billz.databinding.ActivityLoginBinding
import com.example.billz.databinding.ActivityRegistrationBinding

class RegistrationPage : AppCompatActivity(){
    private val handler = Handler(Looper.getMainLooper())
    lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)


//       setContentView(R.layout.activity_registration)
    }
}