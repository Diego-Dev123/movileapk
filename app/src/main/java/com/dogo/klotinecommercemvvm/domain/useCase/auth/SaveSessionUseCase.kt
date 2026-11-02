package com.dogo.klotinecommercemvvm.domain.useCase.auth

import com.dogo.klotinecommercemvvm.domain.model.AuthResponse
import com.dogo.klotinecommercemvvm.domain.repository.AuthRepository

class SaveSessionUseCase constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(authResponse: AuthResponse) = repository.saveSession(authResponse)
}