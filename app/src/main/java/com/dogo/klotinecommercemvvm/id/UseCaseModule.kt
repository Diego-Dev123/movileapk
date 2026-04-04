package com.dogo.klotinecommercemvvm.id

import com.dogo.klotinecommercemvvm.data.dataSource.remote.AuthRemoteDataSource
import com.dogo.klotinecommercemvvm.data.dataSource.remote.AuthRemoteDataSourceImpl
import com.dogo.klotinecommercemvvm.data.repository.AuthRepositoryImpl
import com.dogo.klotinecommercemvvm.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideAuthRepository(authRemoteDataSource: AuthRemoteDataSource): AuthRepository =
        AuthRepositoryImpl(authRemoteDataSource)
}