package com.example.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Model.Error
import com.example.Model.LoginRequest
import com.example.Model.LoginResponse
import com.example.Model.RegistrationRequest
import com.example.Model.RegistrationResponse
import com.example.Repository.UserRepository
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch
import retrofit2.HttpException

class UserViewModel: ViewModel() {
    var userRepository = UserRepository()
    var registrationResponseLiveData = MutableLiveData<RegistrationResponse>()
    var regErrorLiveData =MutableLiveData<String>()
    var loginResponseLiveData = MutableLiveData<LoginResponse>()
    var loginErrorLiveData = MutableLiveData<String>()


    fun registerUser(registrationRequest: RegistrationRequest){
        viewModelScope.launch{
            try {
                val response = userRepository.registerUser(registrationRequest)
                registrationResponseLiveData.postValue(response)
            }
            catch (e : Exception){
                if (e is HttpException){
                    val errorMessage = hasErrorBody(e)?.error
                    regErrorLiveData.postValue(errorMessage)
                }
                else{
                    regErrorLiveData.postValue(e.message)
                }
            }
        }
    }

    fun loginUser(loginRequest: LoginRequest){
        viewModelScope.launch {
            try {
                val response = userRepository.loginUser(loginRequest)
                loginResponseLiveData.postValue(response)
            }
            catch (e : Exception){
                if (e is HttpException){
                    val errorMessage = hasErrorBody(e)?.error
                    loginErrorLiveData.postValue(errorMessage)
                }
                else{
                    loginErrorLiveData.postValue(e.message)
                }
            }
        }
    }
    fun hasErrorBody(httpException: HttpException): Error? {
        var gson = GsonBuilder().setLenient().serializeNulls().create()

        return try {
            gson.fromJson(httpException.response()?.errorBody()?.charStream(), Error::class.java)
        }
        catch (exception : Exception){
            null
        }
    }
}