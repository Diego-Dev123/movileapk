package com.dogo.klotinecommercemvvm.data.dataSource.remote


import com.dogo.klotinecommercemvvm.domain.model.Category
import com.dogo.klotinecommercemvvm.domain.model.TrashedCategory
import retrofit2.Response
import java.io.File

interface CategoryRemoteDataSource {

    suspend fun create(category: Category, file: File): Response<Category>
    suspend fun update(id: String, category: Category): Response<Category>
    suspend fun updatewithImage(id: String, category: Category, file: File): Response<Category>
    suspend fun delete(id: String): Response<Unit>
    suspend fun getCategories(): Response<List<Category>>
    suspend fun getTrash(): Response<List<TrashedCategory>>
    suspend fun restore(id: String): Response<Category>

}
