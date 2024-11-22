package com.dicoding.picodiploma.loginwithanimation.view.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.picodiploma.loginwithanimation.data.repository.AuthRepository
import com.dicoding.picodiploma.loginwithanimation.data.response.SignUpResponse
import kotlinx.coroutines.launch

class SignUpViewModel(private val repository: AuthRepository) : ViewModel() {
    private val _signUpState = MutableLiveData<SignUpState>(SignUpState.Idle)
    val signUpState: LiveData<SignUpState> = _signUpState

    val signUpResponse: LiveData<SignUpResponse> = repository.registerResponse

    fun postRegister(name: String, email: String, pass: String) {
        viewModelScope.launch {
            try {
                _signUpState.value = SignUpState.Loading
                repository.register(name, email, pass)
                _signUpState.value = SignUpState.Success(name)
            } catch (e: Exception) {
                _signUpState.value = SignUpState.Error(e.message ?: "Something Wrong")
            }
        }
    }
}