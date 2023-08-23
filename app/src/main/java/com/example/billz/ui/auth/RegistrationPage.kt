package com.example.billz.ui.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.Model.Constants
import com.example.Model.LoginResponse
import com.example.Model.RegistrationRequest
import com.example.ViewModel.UserViewModel
import com.example.billz.R
import com.example.billz.databinding.ActivityLoginBinding
import com.example.billz.databinding.ActivityRegistrationBinding
import com.example.billz.home.MainActivity

class RegistrationPage : AppCompatActivity() {
    private val handler = Handler(Looper.getMainLooper())
    lateinit var sharedPrefs: SharedPreferences
    lateinit var binding: ActivityRegistrationBinding
    val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        sharedPrefs = getSharedPreferences(Constants.PREFS_FILE, Context.MODE_PRIVATE)
        setContentView(binding.root)

        binding.getStartedRegisterButton.setOnClickListener {
            binding.progressBar3.visibility = View.VISIBLE
            binding.getStartedRegisterButton.visibility = View.GONE
            val firstName = binding.firstNameInput.text.toString()
            if (firstName.isEmpty()) {
                binding.firstNameInput.setError("Enter first name")
            }
            val lastName = binding.lastNameInput.text.toString()
            if (lastName.isEmpty()) {
                binding.lastNameInput.setError("Enter last name")
            }
            val email = binding.emailInput.text.toString()
            if (email.isEmpty()) {
                binding.emailInput.setError("Enter email")
            }
            val phone = binding.phoneInput.text.toString()
            if (phone.isEmpty()) {
                binding.phoneInput.setError("Enter phone number")
            }
            val password = binding.passwordInput.text.toString()
            if (password.isEmpty()) {
                binding.passwordInput.setError("Enter password")
            }

            val registerUser = RegistrationRequest(
                first_name = firstName,
                last_name = lastName,
                email = email,
                phone_number = phone,
                password = password
            )
            userViewModel.registerUser(registerUser)

            redirectUser()
        }

        binding.before.setOnClickListener {
            val intent = Intent(baseContext, LoginPage::class.java)
            startActivity(intent)
        }
    }

    fun redirectUser() {
        //to check whether the user is registered or not

        userViewModel.registrationResponseLiveData.observe(this) { regResponse ->

            if (!regResponse.user.user_id.isNullOrEmpty()) {
                binding.progressBar3.visibility = View.VISIBLE
                binding.getStartedRegisterButton.visibility = View.GONE
                Toast.makeText(baseContext, "Registration successful", Toast.LENGTH_LONG).show()
                startActivity(Intent(baseContext,MainActivity::class.java))
            }
        }
        userViewModel.regErrorLiveData.observe(this) { error ->
            binding.progressBar3.visibility = View.GONE
            binding.getStartedRegisterButton.visibility = View.VISIBLE
            Toast.makeText(baseContext, error, Toast.LENGTH_SHORT).show()

        }
    }
}