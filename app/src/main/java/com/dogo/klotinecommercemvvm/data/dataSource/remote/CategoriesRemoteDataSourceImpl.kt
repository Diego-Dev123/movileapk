package com.dogo.klotinecommercemvvm.data.dataSource.remote


import com.dogo.klotinecommercemvvm.data.dataSource.remote.service.CategoriesService
import com.dogo.klotinecommercemvvm.domain.model.Category
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.File

class CategoriesRemoteDataSourceImpl(private val categoryService: CategoriesService): CategoryRemoteDataSource {


    override suspend fun create(category: Category, file: File): Response<Category> {
        val conecction = file.toURI().toURL().openConnection()
        val mimetype = conecction.contentType //saber si es jpg png etc
        val conentType = "text/plain"
        val requestFile = file.asRequestBody(mimetype.toMediaTypeOrNull())
        val fileFormData = MultipartBody.Part.createFormData("file", file.name, requestFile)
        val nameData = category.name.toRequestBody(conentType.toMediaTypeOrNull())
        val descriptionData = category.description.toRequestBody(conentType.toMediaTypeOrNull())

        return categoryService.create(fileFormData, nameData, descriptionData)
    }
    override suspend fun update(
        id: String,
        category: Category
    ): Response<Category>{

        val contentType = "text/plain"
        val nameData = category.name.toRequestBody(contentType.toMediaTypeOrNull())
        val descriptionData = category.description.toRequestBody(contentType.toMediaTypeOrNull())
        return categoryService.update(id, nameData, descriptionData) // 👈

    }//  = categoryService.update(id, category)

    override suspend fun updatewithImage(
        id: String,
        category: Category,
        file: File
    ): Response<Category> {

        val conecction = file.toURI().toURL().openConnection()
        val mimetype = conecction.contentType //saber si es jpg png etc
        val conentType = "text/plain"
        val requestFile = file.asRequestBody(mimetype.toMediaTypeOrNull())
        val fileFormData = MultipartBody.Part.createFormData("file", file.name, requestFile)
        val nameData = category.name.toRequestBody(conentType.toMediaTypeOrNull())
        val descriptionData = category.description.toRequestBody(conentType.toMediaTypeOrNull())

        return categoryService.updateWithImage(fileFormData, id, nameData, descriptionData)
    }

    override suspend fun delete(id: String): Response<Unit> = categoryService.delete(id)

    override suspend fun getCategories(): Response<List<Category>> = categoryService.getCategories()


}