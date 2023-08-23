package com.example.Api

import com.example.Model.LoginRequest
import com.example.Model.LoginResponse
import com.example.Model.RegistrationRequest
import com.example.Model.RegistrationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("users/register")

    suspend fun registerUser (@Body registrationRequest: RegistrationRequest): RegistrationResponse

    @POST("users/login")

    suspend fun loginUser (@Body loginRequest: LoginRequest): LoginResponse
}