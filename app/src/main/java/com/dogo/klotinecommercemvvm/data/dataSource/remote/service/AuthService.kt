package com.dogo.klotinecommercemvvm.data.dataSource.remote.service

import com.dogo.klotinecommercemvvm.domain.model.AuthResponse
import com.dogo.klotinecommercemvvm.domain.model.LoginRequest
import com.dogo.klotinecommercemvvm.domain.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>


    @POST("auth/register")
    suspend fun register(@Body() user: User): Response<AuthResponse>
}