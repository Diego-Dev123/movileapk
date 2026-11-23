package com.dogo.klotinecommercemvvm.id

import com.dogo.klotinecommercemvvm.data.dataSource.remote.AuthRemoteDataSource
import com.dogo.klotinecommercemvvm.data.repository.AuthRepositoryImpl
import com.dogo.klotinecommercemvvm.data.repository.UsersRepositoryImpl
import com.dogo.klotinecommercemvvm.data.dataSource.local.AuthLocalDataSource
import com.dogo.klotinecommercemvvm.data.dataSource.local.CategoriesLocalDataSource
import com.dogo.klotinecommercemvvm.data.dataSource.local.ProductsLocalDataSource
import com.dogo.klotinecommercemvvm.data.dataSource.remote.CategoryRemoteDataSource
import com.dogo.klotinecommercemvvm.data.dataSource.remote.ProductsRemoteDataSource
import com.dogo.klotinecommercemvvm.data.dataSource.remote.UsersRemoteDataSource
import com.dogo.klotinecommercemvvm.data.repository.CategoriesRespositoryImpl
import com.dogo.klotinecommercemvvm.data.repository.ProductsRepositoryImpl
import com.dogo.klotinecommercemvvm.domain.repository.AuthRepository
import com.dogo.klotinecommercemvvm.domain.repository.CategoriesRepository
import com.dogo.klotinecommercemvvm.domain.repository.ProductRepository
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
    fun provideCategoryRepository(
        categoryRemoteDataSource: CategoryRemoteDataSource,
        categoriesLocalDataSource: CategoriesLocalDataSource,
        productsLocalDataSource: ProductsLocalDataSource
    ): CategoriesRepository=
        CategoriesRespositoryImpl(categoryRemoteDataSource, categoriesLocalDataSource, productsLocalDataSource)

    @Provides
    fun provideProductRepository(productsRemoteDataSource: ProductsRemoteDataSource, producstLocalDataSource: ProductsLocalDataSource): ProductRepository=
        ProductsRepositoryImpl(productsRemoteDataSource, producstLocalDataSource)
}