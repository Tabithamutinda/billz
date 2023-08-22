package com.example.Model

data class RegistrationRequest(
    var first_name: String,
    var last_name: String,
    var email: String,
    var password: String,
    var phone_number: String
)
