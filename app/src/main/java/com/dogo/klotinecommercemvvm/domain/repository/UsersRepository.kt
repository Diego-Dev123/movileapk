package com.dogo.klotinecommercemvvm.domain.repository

import com.dogo.klotinecommercemvvm.domain.model.User
import com.dogo.klotinecommercemvvm.domain.util.Resource
import java.io.File

interface UsersRepository {

    suspend fun update(id: String, user: User): Resource< User>
    suspend fun updatewithImage(id: String, user: User, file: File): Resource<User>



}