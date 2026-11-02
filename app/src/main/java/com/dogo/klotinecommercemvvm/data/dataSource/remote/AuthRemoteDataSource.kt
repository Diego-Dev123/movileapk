package com.dogo.klotinecommercemvvm.data.dataSource.remote

import com.dogo.klotinecommercemvvm.domain.model.AuthResponse
import com.dogo.klotinecommercemvvm.domain.model.User
import retrofit2.Response

interface AuthRemoteDataSource {

    suspend fun login(email: String, password: String): Response<AuthResponse>


    suspend fun register(user: User): Response<AuthResponse>
}