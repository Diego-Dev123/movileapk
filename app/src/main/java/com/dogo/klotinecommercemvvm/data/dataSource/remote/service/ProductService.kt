package com.dogo.klotinecommercemvvm.data.dataSource.remote.service


import com.dogo.klotinecommercemvvm.domain.model.Category
import com.dogo.klotinecommercemvvm.domain.model.Product
import com.dogo.klotinecommercemvvm.domain.model.TrashedProduct
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

interface ProductService {

    //obtener producto
    @GET("products/category/{id_category}")
    suspend fun findByCategory(
        @Path("id_category") idCategory: String
    ): Response<List<Product>>

    //obtener productos
    @GET("products")
    suspend fun findAll(): Response<List<Product>>

    //crear un producto
    @Multipart
    @POST("products")
    suspend fun create(
        @Part files: Array<MultipartBody.Part?>,
        @Part("name") name: RequestBody,
        @Part("description") description: RequestBody,
        @Part("id_category") idCategory: RequestBody,
        @Part("price") price: RequestBody,
    ): Response<Product>




    @Multipart
    @PUT("products/{id}")
    suspend fun update(
        @Path("id") id: String,
        @Part("name") name: RequestBody,
        @Part("description") description: RequestBody,
        @Part("id_category") idCategory: RequestBody,
        @Part("price") price: RequestBody
    ): Response<Product>


//    @Multipart
//    @PUT("products/upload/{id}")
//    suspend fun updateWithImage(
//        @Part files: Array<MultipartBody.Part?>,
//        @Part("name") name: RequestBody,
//        @Part("description") description: RequestBody,
//        @Path("id_category") idCategory: String,
//        @Part("price") price: RequestBody,
//    ): Response<Product>

    @Multipart
    @PUT("products/upload/{id}")
    suspend fun updateWithImage(
        @Path("id") id: String,
        @Part files: Array<MultipartBody.Part?>,
        @Part("name") name: RequestBody,
        @Part("description") description: RequestBody,
        @Part("categoryId") idCategory: RequestBody,
        @Part("price") price: RequestBody,
        @Part("imageToUpdate") imageToUpdate: Array<RequestBody?>
    ): Response<Product>


    //borrado lógico: el backend mueve el producto a la papelera
    @DELETE("products/{id}")
    suspend fun delete(
        @Path("id") id: String,
    ): Response<Unit>

    //papelera: productos eliminados (solo ADMIN)
    @GET("products/trash")
    suspend fun getTrash(): Response<List<TrashedProduct>>

    //restaurar un producto de la papelera (solo ADMIN)
    @PUT("products/{id}/restore")
    suspend fun restore(
        @Path("id") id: String,
    ): Response<Product>






//
//    @Multipart
//    @PUT("categories/{id}")
//    suspend fun update(
//        @Path("id") id: String,
//        @Part("name") name: RequestBody,
//        @Part("description") description: RequestBody,
//    ): Response<Category>
//
//
//    //actualizar una categoria con imagen
//    @Multipart
//    @PUT("categories/upload/{id}")
//    suspend fun updateWithImage(
//        @Part file: MultipartBody.Part,
//        @Path("id") id: String,
//        @Part("name") name: RequestBody,
//        @Part("description") description: RequestBody,
//    ): Response<Category>
//
//    //eliminar una categoria
//    @DELETE("categories/{id}")
//    suspend fun delete(
//        @Path("id") id: String,
//    ): Response<Unit>


}