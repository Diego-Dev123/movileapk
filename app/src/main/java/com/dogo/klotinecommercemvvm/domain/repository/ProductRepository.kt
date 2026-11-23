package com.dogo.klotinecommercemvvm.domain.repository

import com.dogo.klotinecommercemvvm.domain.model.Product
import com.dogo.klotinecommercemvvm.domain.model.TrashedProduct
import com.dogo.klotinecommercemvvm.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import java.io.File

interface ProductRepository {

    fun findAll(): Flow<Resource<List<Product>>>
    fun findByCategory(idCategory: String): Flow<Resource<List<Product>>>
    suspend fun create(product: Product, files: List<File>): Resource<Product>

    suspend fun updateWithImage(id: String, product: Product, files: List<File>?): Resource<Product>

    suspend fun update(id: String, product: Product): Resource<Product>
    suspend fun delete(id: String): Resource<Unit>
    suspend fun getTrash(): Resource<List<TrashedProduct>>
    suspend fun restore(id: String): Resource<Product>

}