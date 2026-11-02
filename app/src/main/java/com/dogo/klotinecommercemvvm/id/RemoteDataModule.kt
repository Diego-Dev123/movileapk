package com.dogo.klotinecommercemvvm.id

import com.dogo.klotinecommercemvvm.data.dataSource.remote.AuthRemoteDataSource
import com.dogo.klotinecommercemvvm.data.dataSource.remote.CategoryRemoteDataSource
import com.dogo.klotinecommercemvvm.data.dataSource.remote.UsersRemoteDataSource
import com.dogo.klotinecommercemvvm.data.dataSource.remote.AuthRemoteDataSourceImpl
import com.dogo.klotinecommercemvvm.data.dataSource.remote.CategoriesRemoteDataSourceImpl
import com.dogo.klotinecommercemvvm.data.dataSource.remote.UsersRemoteDataSourceImpl
import com.dogo.klotinecommercemvvm.data.dataSource.remote.service.AuthService
import com.dogo.klotinecommercemvvm.data.dataSource.remote.service.CategoriesService
import com.dogo.klotinecommercemvvm.data.dataSource.remote.service.UsersService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {
    @Provides
    fun provideAuthRemoteDataSource(authService: AuthService): AuthRemoteDataSource =
        AuthRemoteDataSourceImpl(authService)


    @Provides
    fun provideUsersRemoteDataSource(usersService: UsersService): UsersRemoteDataSource =
        UsersRemoteDataSourceImpl(usersService)

    @Provides
    fun provideCategoryRemoteDataSource(categoryService: CategoriesService): CategoryRemoteDataSource =
        CategoriesRemoteDataSourceImpl(categoryService)



}