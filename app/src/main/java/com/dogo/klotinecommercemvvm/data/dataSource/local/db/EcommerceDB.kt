package com.dogo.klotinecommercemvvm.data.dataSource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dogo.klotinecommercemvvm.data.dataSource.local.dao.CategoriesDao
import com.dogo.klotinecommercemvvm.data.dataSource.local.entity.CategoryEntity

@Database(
    entities = [CategoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class EcommerceDB: RoomDatabase(){
    abstract fun categoryDao(): CategoriesDao //data acces object
}