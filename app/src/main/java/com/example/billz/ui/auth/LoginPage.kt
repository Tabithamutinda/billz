package com.example.billz.ui.auth

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.Model.LoginRequest
import com.example.billz.databinding.ActivityLoginBinding

class LoginPage:  AppCompatActivity(){
    private val handler = Handler(Looper.getMainLooper())
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener{
            binding.progressBar3.visibility = View.VISIBLE

            val email = binding.emailInput.text.toString()
            if (email.isEmpty()) {
                binding.emailInput.setError("Enter email")
            }
            val password = binding.passwordInput.text.toString()
            if (password.isEmpty()) {
                binding.passwordInput.setError("Enter password")
            }
            val logRequest =
                LoginRequest(
                    email = email, password = password
                )
        }
        binding.registerText.setOnClickListener {
            val intent = Intent(baseContext, RegistrationPage::class.java)
            startActivity(intent)
        }

//        setContentView(R.layout.activity_login)


    }

}