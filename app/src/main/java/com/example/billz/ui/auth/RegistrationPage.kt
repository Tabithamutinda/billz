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
import com.example.utils.Constants
import com.example.Model.RegistrationRequest
import com.example.ViewModel.UserViewModel
import com.example.billz.databinding.ActivityRegistrationBinding

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

            val firstName = binding.firstNameInput.text.toString()
            if (firstName.isEmpty()) {
                binding.firstNameInput.setError("Enter first name")
                return@setOnClickListener
            }
            val lastName = binding.lastNameInput.text.toString()
            if (lastName.isEmpty()) {
                binding.lastNameInput.setError("Enter last name")
                return@setOnClickListener
            }
            val email = binding.emailInput.text.toString()
            if (email.isEmpty()) {
                binding.emailInput.setError("Enter email")
                return@setOnClickListener
            }
            val phone = binding.phoneInput.text.toString()
            if (phone.isEmpty()) {
                binding.phoneInput.setError("Enter phone number")
                return@setOnClickListener
            }
            val password = binding.passwordInput.text.toString()
            if (password.isEmpty()) {
                binding.passwordInput.setError("Enter password")
                return@setOnClickListener
            }
            binding.progressBar3.visibility = View.VISIBLE
            binding.getStartedRegisterButton.visibility = View.GONE

            val registerUser = RegistrationRequest(
                firstName = firstName,
                lastName = lastName,
                email = email,
                phoneNumber = phone,
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

    private fun redirectUser() {
        //to check whether the user is registered or not

        userViewModel.registrationResponseLiveData.observe(this) { regResponse ->

            if (!regResponse.user.userId.isNullOrEmpty()) {
                binding.progressBar3.visibility = View.VISIBLE
                binding.getStartedRegisterButton.visibility = View.GONE
                Toast.makeText(baseContext, "Registration successful", Toast.LENGTH_LONG).show()
                startActivity(Intent(baseContext,LoginPage::class.java))
                finish()
            }
        }
        userViewModel.regErrorLiveData.observe(this) { error ->
            binding.progressBar3.visibility = View.GONE
            binding.getStartedRegisterButton.visibility = View.VISIBLE
            Toast.makeText(baseContext, error, Toast.LENGTH_SHORT).show()
        }
    }
}