package com.dogo.klotinecommercemvvm.presentation.screens.roles

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogo.klotinecommercemvvm.domain.model.AuthResponse
import com.dogo.klotinecommercemvvm.domain.useCase.auth.AuthUseCase
import com.dogo.klotinecommercemvvm.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RolesViewModel @Inject constructor(private val authUseCase: AuthUseCase): ViewModel() {

    var authResponse by mutableStateOf(AuthResponse())

    init {
        getSessionData()
    }
    fun getSessionData() = viewModelScope.launch {
        authUseCase.getSessionData().collect(){ data->
            if(!data.token.isNullOrBlank()){
                authResponse = data
            }
        }
    }
}