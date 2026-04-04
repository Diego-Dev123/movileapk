package com.dogo.klotinecommercemvvm.data.dataSource.remote.service

import com.dogo.klotinecommercemvvm.domain.model.AuthResponse
import retrofit2.Response

interface AuthRemoteDataSource {


    suspend fun login(email: String, password: String): Response<AuthResponse>
}
