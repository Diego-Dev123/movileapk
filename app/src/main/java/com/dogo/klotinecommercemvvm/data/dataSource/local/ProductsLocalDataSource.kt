package com.dogo.klotinecommercemvvm.data.dataSource.local


import com.dogo.klotinecommercemvvm.data.dataSource.local.entity.ProductEntity
import com.dogo.klotinecommercemvvm.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsLocalDataSource {

    suspend fun findByCategory(id_category: String) : Flow<List<ProductEntity>>
    suspend fun insert(product: ProductEntity)
    suspend fun insertAll(products: List<ProductEntity>)
    suspend fun findAll(): Flow<List<ProductEntity>>
    suspend fun update(id: String, name: String, description: String, image1: String, image2: String, price: Double)
    suspend fun delete(id: String)
    suspend fun deleteByCategory(idCategory: String)

}