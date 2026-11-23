package com.dogo.klotinecommercemvvm.data.dataSource.remote.service

import com.dogo.klotinecommercemvvm.domain.model.Category
import com.dogo.klotinecommercemvvm.domain.model.TrashedCategory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface CategoriesService {

    //obtener categorias
    @GET("categories")
    suspend fun getCategories(): Response<List<Category>>

    //crear una categoria
    @Multipart
    @POST("categories")
    suspend fun create(
        @Part file: MultipartBody.Part,
        @Part("name") name: RequestBody,
        @Part("description") description: RequestBody,
    ): Response<Category>


//    //actualizar una categoria
//    @PUT("categories/{id}")
//    suspend fun update(
//        @Path("id") id: String,
//        @Body category: Category
//    ): Response<Category>

    @Multipart
    @PUT("categories/{id}")
    suspend fun update(
        @Path("id") id: String,
        @Part("name") name: RequestBody,
        @Part("description") description: RequestBody,
    ): Response<Category>


    //actualizar una categoria con imagen
    @Multipart
    @PUT("categories/upload/{id}")
    suspend fun updateWithImage(
        @Part file: MultipartBody.Part,
        @Path("id") id: String,
        @Part("name") name: RequestBody,
        @Part("description") description: RequestBody,
    ): Response<Category>

    //eliminar una categoria (borrado lógico: el backend la mueve a la papelera)
    @DELETE("categories/{id}")
    suspend fun delete(
        @Path("id") id: String,
    ): Response<Unit>

    //papelera: categorías eliminadas (solo ADMIN)
    @GET("categories/trash")
    suspend fun getTrash(): Response<List<TrashedCategory>>

    //restaurar una categoría de la papelera (solo ADMIN)
    @PUT("categories/{id}/restore")
    suspend fun restore(
        @Path("id") id: String,
    ): Response<Category>


}