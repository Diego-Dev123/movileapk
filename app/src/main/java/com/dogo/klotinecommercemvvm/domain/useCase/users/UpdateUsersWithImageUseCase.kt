package com.dogo.klotinecommercemvvm.domain.useCase.users

import com.dogo.klotinecommercemvvm.domain.model.User
import com.dogo.klotinecommercemvvm.domain.repository.UsersRepository
import java.io.File

class UpdateUsersWithImageUseCase constructor(private val repository: UsersRepository) {

    suspend operator fun invoke(id: String, user: User, file: File) = repository.updatewithImage(id, user, file)
}