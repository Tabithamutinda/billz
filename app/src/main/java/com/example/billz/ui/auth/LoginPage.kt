package com.example.billz.ui.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.opengl.Visibility
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.Model.Constants
import com.example.Model.LoginRequest
import com.example.Model.LoginResponse
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

            val email = binding.emailInput.text.toString()
            if (email.isEmpty()) {
                binding.emailInput.setError("Enter email")
                return@setOnClickListener
            }
            val password = binding.passwordInput.text.toString()
            if (password.isEmpty()) {
                binding.passwordInput.setError("Enter password")
                return@setOnClickListener
            }
            binding.progressBar3.visibility = View.VISIBLE
            binding.loginButton.visibility = View.GONE
            val logRequest =
                LoginRequest(
                    email = email, password = password
                )
            userViewModel.loginUser(logRequest)
            redirectUser()
        }
        binding.registerText.setOnClickListener {
            val intent = Intent(baseContext, RegistrationPage::class.java)
            startActivity(intent)
        }

    }
    private fun redirectUser() {
        //to check whether the user is logged in or not
        userViewModel.loginResponseLiveData.observe(this) { loginResponse ->

            if (!loginResponse.access_token.isNullOrEmpty()) {
                binding.progressBar3.visibility = View.VISIBLE
                binding.loginButton.visibility = View.GONE
                val save = sharedPrefs.edit()
                save.putString("ACCESS_TOKEN", loginResponse.access_token)
                save.putString("USER_ID", loginResponse.user_id)
                save.putBoolean("IS_LOGGED_IN", true)
                save.apply()
                Toast.makeText(baseContext, "Login successful", Toast.LENGTH_LONG).show()
                startActivity(Intent(baseContext,MainActivity::class.java))
                finish()
            }
        }
        userViewModel.loginErrorLiveData.observe(this) { error ->
            val save = sharedPrefs.edit()
            save.putBoolean("IS_LOGGED_IN", false)
            save.apply()
            binding.progressBar3.visibility = View.GONE
            binding.loginButton.visibility = View.VISIBLE
            Toast.makeText(baseContext, error, Toast.LENGTH_SHORT).show()
        }
    }

}