package com.dogo.klotinecommercemvvm.data.dataSource.remote


import com.dogo.klotinecommercemvvm.domain.model.Product
import com.dogo.klotinecommercemvvm.domain.model.TrashedProduct
import retrofit2.Response
import java.io.File

interface ProductsRemoteDataSource {
    suspend fun findAll(): Response<List<Product>>
    suspend fun findByCategory(idCategory: String): Response<List<Product>>
    suspend fun create(product: Product, files: List<File>): Response<Product>
    suspend fun updateWithImage(id: String, product: Product, files: List<File>?): Response<Product>
    suspend fun update(id: String, product: Product): Response<Product>
    suspend fun delete(id: String): Response<Unit>
    suspend fun getTrash(): Response<List<TrashedProduct>>
    suspend fun restore(id: String): Response<Product>
}