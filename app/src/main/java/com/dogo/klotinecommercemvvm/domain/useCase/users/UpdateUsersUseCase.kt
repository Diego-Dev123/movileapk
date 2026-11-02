package com.dogo.klotinecommercemvvm.domain.useCase.users

import com.dogo.klotinecommercemvvm.domain.model.User
import com.dogo.klotinecommercemvvm.domain.repository.UsersRepository

class UpdateUsersUseCase constructor(private val repository: UsersRepository) {

    suspend operator fun invoke(id: String, user: User) = repository.update(id, user)
}