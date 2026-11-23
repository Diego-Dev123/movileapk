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

data class RegisterErrors(
    val name: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val password: String? = null,
    val confirmPassword: String? = null
)

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authUseCase: AuthUseCase): ViewModel() {
    var state by mutableStateOf(RegisterState())
        private set

    var MessageError by mutableStateOf("")

    var errors by mutableStateOf(RegisterErrors())
        private set
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
        errors = errors.copy(name = null)
    }
    fun onLastNameInput(input: String){
        state = state.copy(lastName = input)
        errors = errors.copy(lastName = null)
    }
    fun onEmailInput(input: String){
        state = state.copy(email = input)
        errors = errors.copy(email = null)
    }
    fun onPhoneInput(input: String){
        state = state.copy(phone = input)
        errors = errors.copy(phone = null)
    }
    fun onPasswordInput(input: String){
        state = state.copy(password = input)
        errors = errors.copy(password = null)
    }
    fun onConfirmPasswordInput(input: String){
        state = state.copy(confirmPassword = input)
        errors = errors.copy(confirmPassword = null)
    }

    fun IsvalidateForm(): Boolean {
        val result = RegisterErrors(
            name = if (state.name == "") "Ingresa el nombre!" else null,
            lastName = if (state.lastName == "") "Ingresa el apellido!" else null,
            email = when {
                state.email == "" -> "Ingresa el email!"
                !Patterns.EMAIL_ADDRESS.matcher(state.email).matches() -> "El email no es valido"
                else -> null
            },
            phone = if (state.phone == "") "Ingresa el telefono!" else null,
            password = when {
                state.password == "" -> "Ingresa la contrasena"
                state.password.length < 6 -> "La contraseña debe tener al menos 6 caracteres"
                else -> null
            },
            confirmPassword = when {
                state.confirmPassword == "" -> "Ingresa la confirmacion de contrasena"
                state.password != state.confirmPassword -> "Las contraseñas no coinciden"
                else -> null
            }
        )
        errors = result
        return result == RegisterErrors()
    }
}
