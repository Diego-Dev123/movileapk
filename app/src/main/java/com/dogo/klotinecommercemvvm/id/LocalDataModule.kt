package com.dogo.klotinecommercemvvm.id

import com.dogo.klotinecommercemvvm.data.dataSource.local.dataStore.AuthDataStore
import com.dogo.klotinecommercemvvm.data.dataSource.local.AuthLocalDataSource
import com.dogo.klotinecommercemvvm.data.dataSource.local.AuthLocalDataSourceImpl
import com.dogo.klotinecommercemvvm.data.dataSource.local.CategoriesLocalDataSource
import com.dogo.klotinecommercemvvm.data.dataSource.local.CategoriesLocalDataSourceImpl
import com.dogo.klotinecommercemvvm.data.dataSource.local.dao.CategoriesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Provides
    fun provideAuthLocalDataSource(authDataStore: AuthDataStore): AuthLocalDataSource =
        AuthLocalDataSourceImpl(authDataStore)


    @Provides
    fun providesCategoriesLocalDataSOurce(categoriesDao: CategoriesDao): CategoriesLocalDataSource =
        CategoriesLocalDataSourceImpl(categoriesDao)
}