package com.dicoding.picodiploma.loginwithanimation.view.ui.signup

sealed class SignUpState {
    object Idle : SignUpState()
    object Loading : SignUpState()
    data class Success(val name: String) : SignUpState()
    data class Error(val message: String) : SignUpState()
}