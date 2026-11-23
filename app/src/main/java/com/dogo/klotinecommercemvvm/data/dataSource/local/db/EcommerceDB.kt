package com.dogo.klotinecommercemvvm.data.dataSource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dogo.klotinecommercemvvm.data.dataSource.local.dao.CategoriesDao
import com.dogo.klotinecommercemvvm.data.dataSource.local.dao.ProductsDao
import com.dogo.klotinecommercemvvm.data.dataSource.local.entity.CategoryEntity
import com.dogo.klotinecommercemvvm.data.dataSource.local.entity.ProductEntity

@Database(
    entities = [CategoryEntity::class, ProductEntity::class],
    version = 2,
    exportSchema = false
)
abstract class EcommerceDB: RoomDatabase(){
    abstract fun categoryDao(): CategoriesDao //data acces object
    abstract fun productDao(): ProductsDao
}
