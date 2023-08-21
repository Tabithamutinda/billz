package com.example.billz.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.billz.databinding.ActivityGetStartedBinding
import com.example.billz.R

class GetStarted : AppCompatActivity(){
    lateinit var binding: ActivityGetStartedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetStartedBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_get_started)
        setContentView(binding.root)

        binding.getStartedRegisterButton.setOnClickListener {
            val intent = Intent(baseContext, RegistrationPage::class.java)
            startActivity(intent)
        }

        binding.getStartedLoginButton.setOnClickListener {
            val intent = Intent(baseContext, LoginPage::class.java)
            startActivity(intent)
        }

    }
}