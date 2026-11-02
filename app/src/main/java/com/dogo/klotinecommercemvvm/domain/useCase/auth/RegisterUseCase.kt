package com.dogo.klotinecommercemvvm.domain.useCase.auth

import com.dogo.klotinecommercemvvm.domain.model.User
import com.dogo.klotinecommercemvvm.domain.repository.AuthRepository

data class RegisterUseCase(private val repository: AuthRepository){

    suspend operator fun invoke(user: User) = repository.register(user)
}
