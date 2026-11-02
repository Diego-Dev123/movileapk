package com.dogo.klotinecommercemvvm.data.dataSource.remote

import com.dogo.klotinecommercemvvm.data.dataSource.remote.service.AuthService
import com.dogo.klotinecommercemvvm.domain.model.AuthResponse
import com.dogo.klotinecommercemvvm.domain.model.LoginRequest
import com.dogo.klotinecommercemvvm.domain.model.User
import retrofit2.Response

class AuthRemoteDataSourceImpl(private val authService: AuthService): AuthRemoteDataSource {
    override suspend fun login(
        email: String,
        password: String
    ): Response<AuthResponse> = authService.login(LoginRequest(email, password))

    override suspend fun register(user: User): Response<AuthResponse> =authService.register(user)

}