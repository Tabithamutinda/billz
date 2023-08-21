package com.example.billz.ui.auth

import android.content.Intent
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

        binding.getStartedRegisterButton.setOnClickListener{
            val firstName = binding.firstNameInput.text.toString()
            if(firstName.isEmpty()){
                binding.firstNameInput.setError("Enter first name")
            }
            val lastName = binding.lastNameInput.text.toString()
            if(lastName.isEmpty()){
                binding.lastNameInput.setError("Enter last name")
            }
            val email = binding.emailInput.text.toString()
            if(email.isEmpty()){
                binding.emailInput.setError("Enter email")
            }
            val phone = binding.phoneInput.text.toString()
            if(phone.isEmpty()){
                binding.phoneInput.setError("Enter phone number")
            }
            val password = binding.passwordInput.text.toString()
            if(password.isEmpty()){
                binding.passwordInput.setError("Enter password")
            }
        }
        binding.before.setOnClickListener{
            val intent = Intent(baseContext, LoginPage::class.java)
            startActivity(intent)
        }
    }
}