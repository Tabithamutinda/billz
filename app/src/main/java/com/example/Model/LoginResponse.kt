package com.example.Model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val message: String,
    @SerializedName("access_token")
    val access_token: String,
    @SerializedName("user_id")
    val user_id: String
)
