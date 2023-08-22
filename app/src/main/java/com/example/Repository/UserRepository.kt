package com.example.Repository

import com.example.Api.ApiClient
import com.example.Api.ApiInterface
import com.example.Model.LoginRequest
import com.example.Model.LoginResponse
import com.example.Model.RegistrationRequest
import com.example.Model.RegistrationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit

class UserRepository {
    val retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun registerUser(registrationRequest: RegistrationRequest):
            Response<RegistrationResponse> = withContext(Dispatchers.IO) {
        var response = retrofit.registerUser(registrationRequest)
        return@withContext response;
    }

    suspend fun loginUser(loginRequest: LoginRequest):
            Response<LoginResponse> = withContext(Dispatchers.IO) {
                var response = retrofit.loginUser(loginRequest)
        return@withContext response;
    }
}