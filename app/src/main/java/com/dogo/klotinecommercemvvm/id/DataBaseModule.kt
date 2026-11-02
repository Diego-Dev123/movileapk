package com.dogo.klotinecommercemvvm.id

import android.app.Application
import androidx.room.Room
import com.dogo.klotinecommercemvvm.data.dataSource.local.dao.CategoriesDao
import com.dogo.klotinecommercemvvm.data.dataSource.local.db.EcommerceDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {


    @Provides
    @Singleton
    fun provideDataBase(app: Application): EcommerceDB =
        Room.databaseBuilder(app, EcommerceDB::class.java, "ecommerce_db").fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideCategoryDao(db: EcommerceDB): CategoriesDao = db.categoryDao()
}