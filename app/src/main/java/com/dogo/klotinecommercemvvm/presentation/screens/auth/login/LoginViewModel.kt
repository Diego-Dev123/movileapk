package com.dogo.klotinecommercemvvm.presentation.screens.auth.login

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {
    var state by mutableStateOf(LoginState())
        private set

    var MessageError by mutableStateOf("")
        private set
    var isValidForm by mutableStateOf(false)
        private set


    //inputs del login trabajando formulario
    fun onEmailInput(email: String) {
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String) {
        state = state.copy(password = password)
    }

    //validar login
    fun validateForm() = viewModelScope.launch {
        if (!Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            MessageError = "El email no es valido"
        } else if (state.password.length < 6) {
            MessageError = "La contraseña debe tener al menos 6 caracteres"
        }

        delay(3000)
        MessageError = ""
    }
}