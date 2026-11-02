package com.dogo.klotinecommercemvvm.data.dataSource.local

import com.dogo.klotinecommercemvvm.data.dataSource.local.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

interface CategoriesLocalDataSource {

    suspend fun create(category: CategoryEntity)
    suspend fun insertAll(categories: List<CategoryEntity>)
    suspend fun getCategories(): Flow<List<CategoryEntity>>
    suspend fun update(id: String, name: String, description: String, image: String)
    suspend fun delete(id: String)

}