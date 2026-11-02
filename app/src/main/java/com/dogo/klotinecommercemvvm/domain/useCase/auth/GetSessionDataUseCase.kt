package com.dogo.klotinecommercemvvm.domain.useCase.auth

import com.dogo.klotinecommercemvvm.domain.repository.AuthRepository

class GetSessionDataUseCase constructor(private val repository: AuthRepository) {
    operator fun invoke() = repository.getSessionData()
}