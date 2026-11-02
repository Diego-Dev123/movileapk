package com.dogo.klotinecommercemvvm.data.dataSource.remote

import com.dogo.klotinecommercemvvm.domain.model.User
import retrofit2.Response
import java.io.File

interface UsersRemoteDataSource {
    suspend fun update(id: String, user: User): Response<User>
    suspend fun updatewithImage(id: String, user: User, file: File): Response<User>
}