package com.dogo.klotinecommercemvvm.domain.useCase.auth

import com.dogo.klotinecommercemvvm.domain.model.User
import com.dogo.klotinecommercemvvm.domain.repository.AuthRepository

class UpdateSessionUseCase constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(user: User) = repository.updateSession(user)
}