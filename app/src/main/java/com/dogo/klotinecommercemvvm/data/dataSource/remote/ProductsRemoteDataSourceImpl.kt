package com.dogo.klotinecommercemvvm.data.dataSource.remote

import com.dogo.klotinecommercemvvm.data.dataSource.remote.service.ProductService
import com.dogo.klotinecommercemvvm.domain.model.Product
import com.dogo.klotinecommercemvvm.domain.model.TrashedProduct
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.File

class ProductsRemoteDataSourceImpl(private val productService: ProductService): ProductsRemoteDataSource {
    override suspend fun findAll(): Response<List<Product>> = productService.findAll()

    override suspend fun findByCategory(idCategory: String): Response<List<Product>> = productService.findByCategory(idCategory)

    override suspend fun create(
        product: Product,
        files: List<File>
    ): Response<Product> {
        val images = arrayOfNulls<MultipartBody.Part>(files.size)
        
        files.forEachIndexed { index, file ->
            val conecction = file.toURI().toURL().openConnection()
            val mimetype = conecction.contentType //saber si es jpg png etc
            val requestFile = file.asRequestBody(mimetype.toMediaTypeOrNull())
            images[index] = MultipartBody.Part.createFormData("files[]", file.name, requestFile)

        }


        val conentType = "text/plain"
        val nameData = product.name.toRequestBody(conentType.toMediaTypeOrNull())
        val descriptionData = product.description.toRequestBody(conentType.toMediaTypeOrNull())
        val priceData = product.price.toString().toRequestBody(conentType.toMediaTypeOrNull())
        val idCategoryData = product.idCategory.toRequestBody(conentType.toMediaTypeOrNull())

        return productService.create(images, nameData, descriptionData, idCategoryData,priceData)
    }

    override suspend fun updateWithImage(
        id: String,
        product: Product,
        files: List<File>?
    ): Response<Product> {
        val images = arrayOfNulls<MultipartBody.Part>(files?.size?:0)

        val imageToUpdate = arrayOfNulls<RequestBody>(product.imageToUpdate?.size?:0)

        files?.forEachIndexed { index, file ->
            val conecction = file.toURI().toURL().openConnection()
            val mimetype = conecction.contentType //saber si es jpg png etc
            val requestFile = file.asRequestBody(mimetype.toMediaTypeOrNull())
            images[index] = MultipartBody.Part.createFormData("files", file.name, requestFile)

        }
        



        val contentType = "text/plain"
        product.imageToUpdate?.forEachIndexed { index, position ->
            imageToUpdate[index] = position.toString().toRequestBody(contentType.toMediaTypeOrNull())
        }
        val nameData = product.name.toRequestBody(contentType.toMediaTypeOrNull())
        val descriptionData = product.description.toRequestBody(contentType.toMediaTypeOrNull())
        val priceData = product.price.toString().toRequestBody(contentType.toMediaTypeOrNull())
        val idCategoryData = product.idCategory.toRequestBody(contentType.toMediaTypeOrNull())

        return productService.updateWithImage(id,images, nameData, descriptionData, idCategoryData,priceData, imageToUpdate)
    }




    override suspend fun update(
        id: String,
        product: Product
    ): Response<Product> {

        val contentType = "text/plain"

        val nameData = product.name.toRequestBody(contentType.toMediaTypeOrNull())
        val descriptionData = product.description.toRequestBody(contentType.toMediaTypeOrNull())
        val categoryData = product.idCategory.toString().toRequestBody(contentType.toMediaTypeOrNull())
        val priceData = product.price.toString().toRequestBody(contentType.toMediaTypeOrNull())

        return productService.update(
            id,
            nameData,
            descriptionData,
            categoryData,
            priceData
        )
    }


    override suspend fun delete(id: String): Response<Unit> = productService.delete(id)

    override suspend fun getTrash(): Response<List<TrashedProduct>> = productService.getTrash()

    override suspend fun restore(id: String): Response<Product> = productService.restore(id)
}