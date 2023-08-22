package com.example.billz.ui.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.Model.Constants
import com.example.Model.LoginRequest
import com.example.ViewModel.UserViewModel
import com.example.billz.databinding.ActivityLoginBinding
import com.example.billz.home.MainActivity

class LoginPage:  AppCompatActivity(){
    private val handler = Handler(Looper.getMainLooper())
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPrefs: SharedPreferences
    val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        sharedPrefs = getSharedPreferences(Constants.PREFS_FILE, Context.MODE_PRIVATE)
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
            userViewModel.loginUser(logRequest)

            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
        }
        binding.registerText.setOnClickListener {
            val intent = Intent(baseContext, RegistrationPage::class.java)
            startActivity(intent)
        }



    }

}