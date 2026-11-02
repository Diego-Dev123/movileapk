package com.dogo.klotinecommercemvvm.presentation.screens.auth.login

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogo.klotinecommercemvvm.domain.model.AuthResponse
import com.dogo.klotinecommercemvvm.domain.useCase.auth.AuthUseCase
import com.dogo.klotinecommercemvvm.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCase: AuthUseCase): ViewModel() {
    var state by mutableStateOf(LoginState())
        private set
    var MessageError by mutableStateOf("")

    var isValidForm by mutableStateOf(false)
        private set

    var loginResponse by mutableStateOf <Resource <AuthResponse>?>(null)

    init {
        getSessionData()
    }

    fun getSessionData() = viewModelScope.launch {
        authUseCase.getSessionData().collect(){ data->
            if(!data.token.isNullOrBlank()){
                loginResponse = Resource.Success(data)
            }
        }
    }

    fun saveSession(authResponse: AuthResponse) = viewModelScope.launch {
        authUseCase.saveSession(authResponse)
    }


    //inputs del login trabajando formulario
    fun onEmailInput(email: String) {
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String) {
        state = state.copy(password = password)
    }
    fun login() = viewModelScope.launch {
        if(isValidateForm()){
            loginResponse = Resource.Loading
            val result = authUseCase.login(state.email, state.password)
            loginResponse = result
            Log.d("LoginViewModel", "login: ${loginResponse}")
        }
    }

    //validar logi
    fun isValidateForm(): Boolean {
        if (!Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            MessageError = "El email no es valido"
            return false
        } else if (state.password.length < 6) {
            MessageError = "La contraseña debe tener al menos 6 caracteres"
            return false
        }
        return true;
    }
}