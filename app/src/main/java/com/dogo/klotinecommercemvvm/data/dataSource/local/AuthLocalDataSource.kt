package com.dogo.klotinecommercemvvm.data.dataSource.local

import com.dogo.klotinecommercemvvm.domain.model.AuthResponse
import com.dogo.klotinecommercemvvm.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthLocalDataSource {
    suspend fun saveSession(authResponse: AuthResponse)
    suspend fun updateSession(user: User)
    suspend fun logout()
    fun getSessionData(): Flow<AuthResponse>
}