package com.dogo.klotinecommercemvvm.data.repository

import android.util.Log
import com.dogo.klotinecommercemvvm.data.dataSource.local.CategoriesLocalDataSource
import com.dogo.klotinecommercemvvm.data.dataSource.local.ProductsLocalDataSource
import com.dogo.klotinecommercemvvm.data.dataSource.local.entity.CategoryEntity
import com.dogo.klotinecommercemvvm.data.dataSource.remote.CategoryRemoteDataSource
import com.dogo.klotinecommercemvvm.data.mapper.toCategory
import com.dogo.klotinecommercemvvm.data.mapper.toCategoryEntity
import com.dogo.klotinecommercemvvm.domain.model.Category
import com.dogo.klotinecommercemvvm.domain.model.TrashedCategory
import com.dogo.klotinecommercemvvm.domain.util.asTrashResult
import com.dogo.klotinecommercemvvm.domain.repository.CategoriesRepository
import com.dogo.klotinecommercemvvm.domain.util.Resource
import com.dogo.klotinecommercemvvm.domain.util.ResponseToRequest
import com.dogo.klotinecommercemvvm.domain.util.isListEqual
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.File

class CategoriesRespositoryImpl(
    private val remoteDataSource: CategoryRemoteDataSource,
    private val localDataSource: CategoriesLocalDataSource,
    private val productsLocalDataSource: ProductsLocalDataSource
):
    CategoriesRepository {


    override suspend fun create(category: Category, file: File): Resource<Category>{
        ResponseToRequest.send(remoteDataSource.create(category, file)).run {
            return when(this){
                is Resource.Success ->{
                    localDataSource.create(this.data.toCategoryEntity())
                    Resource.Success(this.data)
                }
                else-> {
                    Resource.Failure("Error Desconocido")
                }
            }
        }
    }


    override fun getCategpries(): Flow<Resource<List<Category>>> = flow {
        localDataSource.getCategories().collect { cached ->
            emit(syncWithRemote(cached))
        }
    }.flowOn(Dispatchers.IO)

    //el servidor manda; la caché solo responde si no hay conexion
    private suspend fun syncWithRemote(cached: List<CategoryEntity>): Resource<List<Category>> {
        val result = try {
            ResponseToRequest.send(remoteDataSource.getCategories())
        } catch (e: Exception) {
            Resource.Failure(e.message ?: "No se pudo conectar con el servidor")
        }
        return when (result) {
            is Resource.Success -> {
                val remote = result.data.map { it.toCategoryEntity() }
                val remoteIds = remote.map { it.id }.toSet()
                cached.filter { it.id !in remoteIds }.forEach { localDataSource.delete(it.id) }
                if (cached.filter { it.id in remoteIds }.toSet() != remote.toSet()) {
                    localDataSource.insertAll(remote)
                }
                result
            }
            is Resource.Failure -> cachedOrFailure(cached, result.message)
            else -> cachedOrFailure(cached, null)
        }
    }

    private fun cachedOrFailure(cached: List<CategoryEntity>, message: String?): Resource<List<Category>> =
        if (cached.isEmpty()) {
            Resource.Failure(message ?: "No se pudo conectar con el servidor")
        } else {
            Resource.Success(cached.map { it.toCategory() })
        }





//            trySend(ResponseToRequest.send(remoteDataSource.getCategories()))
//        awaitClose {
//            cancel()
//        }



    override suspend fun update(
        id: String,
        category: Category
    ): Resource<Category> {
        ResponseToRequest.send(remoteDataSource.update(id, category)).run {
            return when(this){
                is Resource.Success ->{
                    localDataSource.update(
                        id = this.data.id ?: "",
                        name = this.data.name,
                        description = this.data.description,
                        image = this.data.image ?: ""
                    )
                    Resource.Success(this.data)
                }
                else-> {
                    Resource.Failure("Error Desconocido")
                }
            }
        }
    }

    override suspend fun updateWithImage(
        id: String,
        category: Category,
        file: File
    ): Resource<Category> {
        ResponseToRequest.send(remoteDataSource.updatewithImage(id, category,file)).run {
            return when(this){
                is Resource.Success ->{
                    localDataSource.update(
                        id = this.data.id ?: "",
                        name = this.data.name,
                        description = this.data.description,
                        image = this.data.image ?: ""
                    )
                    Resource.Success(this.data)
                }
                else-> {
                    Resource.Failure("Error Desconocido")
                }
            }
        }

    }

    override suspend fun delete(id: String): Resource<Unit> {
        val result = ResponseToRequest.send(remoteDataSource.delete(id)).asTrashResult()
        return when(result){
            is Resource.Success ->{
                //solo después de la confirmación del backend se ocultan categoría y productos en local
                localDataSource.delete(id)
                productsLocalDataSource.deleteByCategory(id)
                Resource.Success(Unit)
            }
            is Resource.Failure -> result
            else-> {
                Resource.Failure("Error Desconocido")
            }
        }
    }

    override suspend fun getTrash(): Resource<List<TrashedCategory>> =
        try {
            ResponseToRequest.send(remoteDataSource.getTrash()).asTrashResult()
        } catch (e: Exception) {
            Resource.Failure(e.message ?: "Error al consultar la papelera")
        }

    override suspend fun restore(id: String): Resource<Category> {
        val result = try {
            ResponseToRequest.send(remoteDataSource.restore(id)).asTrashResult()
        } catch (e: Exception) {
            Resource.Failure(e.message ?: "Error al restaurar la categoría")
        }

        if (result is Resource.Success) {
            //la categoría vuelve a la caché local; el Flow de Room refresca el listado activo
            localDataSource.create(result.data.toCategoryEntity())
        }
        return result
    }


    fun emitDataSource(categoriesLocal: List<CategoryEntity>) = flow {
        try {
            ResponseToRequest.send(remoteDataSource.getCategories()).run {
                when (this) {
                    is Resource.Success -> {
                        Log.d("CategoriesRespositoryImpl", "data remote: $this")
                        val categoriesRemote = this.data
                        val categoriesLocalMap = categoriesLocal.map {
                            categoryEntity -> categoryEntity.toCategory()
                        }

                        localDataSource.insertAll(this.data.map { category -> category.toCategoryEntity() })
                        emit(Resource.Success(this.data))
                    }

                    else -> {}
                }
            }
        }catch (e: Exception){

        }
    }
}