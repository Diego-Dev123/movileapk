package com.dogo.klotinecommercemvvm.id

import com.dogo.klotinecommercemvvm.data.dataSource.remote.AuthRemoteDataSource
import com.dogo.klotinecommercemvvm.data.repository.AuthRepositoryImpl
import com.dogo.klotinecommercemvvm.data.repository.UsersRepositoryImpl
import com.dogo.klotinecommercemvvm.data.dataSource.local.AuthLocalDataSource
import com.dogo.klotinecommercemvvm.data.dataSource.local.CategoriesLocalDataSource
import com.dogo.klotinecommercemvvm.data.dataSource.remote.CategoryRemoteDataSource
import com.dogo.klotinecommercemvvm.data.dataSource.remote.UsersRemoteDataSource
import com.dogo.klotinecommercemvvm.data.repository.CategoriesRespositoryImpl
import com.dogo.klotinecommercemvvm.domain.repository.AuthRepository
import com.dogo.klotinecommercemvvm.domain.repository.CategoriesRepository
import com.dogo.klotinecommercemvvm.domain.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideAuthRepository(authRemoteDataSource: AuthRemoteDataSource, authLocalDataSource: AuthLocalDataSource): AuthRepository =
        AuthRepositoryImpl(authRemoteDataSource, authLocalDataSource)


    @Provides
    fun provideUsersRepository(usersRemoteDataSource: UsersRemoteDataSource): UsersRepository =
        UsersRepositoryImpl(usersRemoteDataSource)

    @Provides
    fun provideCategoryRepository(categoryRemoteDataSource: CategoryRemoteDataSource, categoriesLocalDataSource: CategoriesLocalDataSource): CategoriesRepository=
        CategoriesRespositoryImpl(categoryRemoteDataSource, categoriesLocalDataSource)
}