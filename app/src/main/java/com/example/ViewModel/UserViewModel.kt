package com.example.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Model.LoginRequest
import com.example.Model.LoginResponse
import com.example.Model.RegistrationRequest
import com.example.Model.RegistrationResponse
import com.example.Repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {
    var userRepository = UserRepository()
    var registrationResponseLiveData = MutableLiveData<RegistrationResponse>()
    var regErrorLiveData =MutableLiveData<String>()
    var loginResponseLiveData = MutableLiveData<LoginResponse>()
    var loginErrorLiveData = MutableLiveData<String>()


    fun registerUser(registrationRequest: RegistrationRequest){
        viewModelScope.launch{
            var response = userRepository.registerUser(registrationRequest)
            if (response.isSuccessful){
                registrationResponseLiveData.postValue(response.body())
            }
            else{
                regErrorLiveData.postValue(response.errorBody()?.toString())
            }
        }
    }

    fun loginUser(loginRequest: LoginRequest){
        viewModelScope.launch {
            var response = userRepository.loginUser(loginRequest)
            if (response.isSuccessful){
                loginResponseLiveData.postValue(response.body())
            }
            else{
                loginErrorLiveData.postValue(response.errorBody()?.toString())
            }
        }
    }
}