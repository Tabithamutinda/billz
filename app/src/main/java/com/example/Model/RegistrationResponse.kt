package com.example.Model

data class RegistrationResponse(
    val message: String,
    val user: UserData
)

data class UserData(
    val phone_number: String,
    val first_name: String,
    val user_id: String,
    val last_name: String,
    val email: String
)

