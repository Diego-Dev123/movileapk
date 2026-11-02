package com.dogo.klotinecommercemvvm.data.dataSource.local

import com.dogo.klotinecommercemvvm.data.dataSource.local.dataStore.AuthDataStore
import com.dogo.klotinecommercemvvm.domain.model.AuthResponse
import com.dogo.klotinecommercemvvm.domain.model.User
import kotlinx.coroutines.flow.Flow

class AuthLocalDataSourceImpl constructor(private val authDataStore: AuthDataStore): AuthLocalDataSource {
    override suspend fun saveSession(authResponse: AuthResponse) = authDataStore.save(authResponse)

    override suspend fun updateSession(user: User) = authDataStore.update(user)

    override suspend fun logout() = authDataStore.delete()

    override fun getSessionData(): Flow<AuthResponse> = authDataStore.getData()
}