package com.dogo.klotinecommercemvvm.presentation.screens.auth.register

import android.util.Patterns
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(): ViewModel() {
    var state by mutableStateOf(RegisterState())
        private set

    var MessageError by mutableStateOf("")
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

    fun validateForm() = viewModelScope.launch {
        if (state.name == ""){
            MessageError = "Ingresa el nombre!"
        }
        else if (state.lastName == ""){
            MessageError = "Ingresa el apellido!"
        }
        else if (state.email == ""){
            MessageError = "Ingresa el email!"
        }
        else if (state.phone == ""){
            MessageError = "Ingresa el telefono!"
        }

        else if (state.password == ""){
            MessageError = "Ingresa la contrasena"
        }
        else if (state.confirmPassword == ""){
            MessageError = "Ingresa la confirmacion de contrasena"
        }


        else if (!Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            MessageError = "El email no es valido"
        } else if (state.password.length < 6) {
            MessageError = "La contraseña debe tener al menos 6 caracteres"
        }
        if (state.password != state.confirmPassword){
            MessageError = "Las contraseñas no coinciden"
        }

        delay(3000)
        MessageError = ""
    }


}