package com.dogo.klotinecommercemvvm.presentation.screens.auth.register

import android.util.Patterns
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogo.klotinecommercemvvm.domain.model.AuthResponse
import com.dogo.klotinecommercemvvm.domain.model.User
import com.dogo.klotinecommercemvvm.domain.useCase.auth.AuthUseCase
import com.dogo.klotinecommercemvvm.domain.util.Resource
import com.dogo.klotinecommercemvvm.presentation.screens.auth.register.mapper.toUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authUseCase: AuthUseCase): ViewModel() {
    var state by mutableStateOf(RegisterState())
        private set

    var MessageError by mutableStateOf("")
    var registerResponse by mutableStateOf<Resource<AuthResponse>?>(null)
        private set


    fun saveSession(authResponse: AuthResponse) = viewModelScope.launch {
        authUseCase.saveSession(authResponse)
    }

    fun register() = viewModelScope.launch {
        if (IsvalidateForm()){
            registerResponse = Resource.Loading
            val result = authUseCase.register(state.toUser())
            registerResponse = result //DATA O ERROR
        }
    }

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

    fun IsvalidateForm(): Boolean {
        if (state.name == ""){
            MessageError = "Ingresa el nombre!"
            return false
        }
        else if (state.lastName == ""){
            MessageError = "Ingresa el apellido!"
            return false
        }
        else if (state.email == ""){
            MessageError = "Ingresa el email!"
            return false
        }
        else if (state.phone == ""){
            MessageError = "Ingresa el telefono!"
            return false
        }

        else if (state.password == ""){
            MessageError = "Ingresa la contrasena"
            return false
        }
        else if (state.confirmPassword == ""){
            MessageError = "Ingresa la confirmacion de contrasena"
            return false
        }


        else if (!Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            MessageError = "El email no es valido"
            return false
        } else if (state.password.length < 6) {
            MessageError = "La contraseña debe tener al menos 6 caracteres"
            return false

        }
        else if (state.password != state.confirmPassword) {
            MessageError = "Las contraseñas no coinciden"
            return false
        }
        return true

    }


}