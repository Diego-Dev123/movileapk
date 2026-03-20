package com.dogo.klotinecommercemvvm.presentation.screens.auth.register

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(): ViewModel() {
    var state by mutableStateOf(RegisterState())
        private set

    fun onNameInput(input: String){
        state = state.copy(name = input)
    }
    fun onLastNameInput(input: String){
        state = state.copy(lastName = input)
    }
    fun onEmailInput(input: String){
        state = state.copy(email = input)
    }
    fun onPhoneInput(input: String){
        state = state.copy(phone = input)
    }
    fun onPasswordInput(input: String){
        state = state.copy(password = input)
    }
    fun onConfirmPasswordInput(input: String){
        state = state.copy(confirmPassword = input)
    }


}