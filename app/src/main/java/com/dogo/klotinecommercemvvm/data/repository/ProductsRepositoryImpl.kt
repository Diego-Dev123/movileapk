package com.dogo.klotinecommercemvvm.data.repository

import com.dogo.klotinecommercemvvm.data.dataSource.local.ProductsLocalDataSource
import com.dogo.klotinecommercemvvm.data.dataSource.local.entity.ProductEntity
import retrofit2.Response
import com.dogo.klotinecommercemvvm.data.dataSource.remote.ProductsRemoteDataSource
import com.dogo.klotinecommercemvvm.data.mapper.toProduct
import com.dogo.klotinecommercemvvm.data.mapper.toProductEntity
import com.dogo.klotinecommercemvvm.domain.model.Product
import com.dogo.klotinecommercemvvm.domain.model.TrashedProduct
import com.dogo.klotinecommercemvvm.domain.util.asTrashResult
import com.dogo.klotinecommercemvvm.domain.repository.ProductRepository
import com.dogo.klotinecommercemvvm.domain.util.Resource
import com.dogo.klotinecommercemvvm.domain.util.ResponseToRequest
import com.dogo.klotinecommercemvvm.domain.util.isListEqual
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import java.io.File

class ProductsRepositoryImpl(
    private val remoteDataSource: ProductsRemoteDataSource,
    private val localDataSource: ProductsLocalDataSource
    ): ProductRepository {


    override fun findAll(): Flow<Resource<List<Product>>> = flow {
        localDataSource.findAll().collect { cached ->
            emit(syncWithRemote(cached) { remoteDataSource.findAll() })
        }
    }.flowOn(Dispatchers.IO)

    override fun findByCategory(idCategory: String): Flow<Resource<List<Product>>> = flow {
        localDataSource.findByCategory(idCategory).collect { cached ->
            emit(syncWithRemote(cached) { remoteDataSource.findByCategory(idCategory) })
        }
    }.flowOn(Dispatchers.IO)

    //el servidor manda; la caché solo responde si no hay conexion
    private suspend fun syncWithRemote(
        cached: List<ProductEntity>,
        fetch: suspend () -> Response<List<Product>>
    ): Resource<List<Product>> {
        val result = try {
            ResponseToRequest.send(fetch())
        } catch (e: Exception) {
            Resource.Failure(e.message ?: "No se pudo conectar con el servidor")
        }
        return when (result) {
            is Resource.Success -> {
                val remote = result.data.map { it.toProductEntity() }
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

    private fun cachedOrFailure(cached: List<ProductEntity>, message: String?): Resource<List<Product>> =
        if (cached.isEmpty()) {
            Resource.Failure(message ?: "No se pudo conectar con el servidor")
        } else {
            Resource.Success(cached.map { it.toProduct() })
        }


    override suspend fun create(
        product: Product,
        files: List<File>
    ): Resource<Product> {
        ResponseToRequest.send(remoteDataSource.create(product, files)).run {
            return when(this){
                is  Resource.Success -> {
                    localDataSource.insert(this.data.toProductEntity())
                    Resource.Success(this.data)
                }
                else -> (Resource.Failure("error desconocido al crear la categoria"))
            }
        }
    }

    override suspend fun update(
        id: String,
        product: Product,
    ): Resource<Product> {
        ResponseToRequest.send(remoteDataSource.update(id, product)).run {
            return when(this){
                is  Resource.Success -> {
                    localDataSource.update(
                        id = this.data.id ?: "",
                        name = this.data.name,
                        description = this.data.description,
                        image1 = this.data.image1 ?: "",
                        image2 = this.data.image2 ?: "",
                        price = this.data.price
                    )
                    Resource.Success(this.data)
                }
                else -> (Resource.Failure("error desconocido al editar"))
            }
        }
    }

    override suspend fun updateWithImage(
        id: String,
        product: Product,
        files: List<File>?
    ): Resource<Product> {
        ResponseToRequest.send(remoteDataSource.updateWithImage(id, product, files)).run {
            return when(this){
                is  Resource.Success -> {
                    localDataSource.update(
                        id = this.data.id ?: "",
                        name = this.data.name,
                        description = this.data.description,
                        image1 = this.data.image1 ?: "",
                        image2 = this.data.image2 ?: "",
                        price = this.data.price
                    )
                    Resource.Success(this.data)
                }
                else -> (Resource.Failure("error desconocido al editar"))
            }
        }
    }

    override suspend fun delete(id: String): Resource<Unit> {
        val result: Resource<Unit> =
            ResponseToRequest.send(remoteDataSource.delete(id))

        return when (result) {
            is Resource.Success<*> -> {
                localDataSource.delete(id)
                Resource.Success(Unit)
            }

            is Resource.Failure -> result.asTrashResult()

            else -> Resource.Failure("Error desconocido al eliminar")
        }
    }

    override suspend fun getTrash(): Resource<List<TrashedProduct>> =
        try {
            ResponseToRequest.send(remoteDataSource.getTrash()).asTrashResult()
        } catch (e: Exception) {
            Resource.Failure(e.message ?: "Error al consultar la papelera")
        }

    override suspend fun restore(id: String): Resource<Product> {
        val result = try {
            ResponseToRequest.send(remoteDataSource.restore(id)).asTrashResult(
                conflictMessage = "No se puede restaurar el producto porque su categoría está eliminada. Restaura primero la categoría."
            )
        } catch (e: Exception) {
            Resource.Failure(e.message ?: "Error al restaurar el producto")
        }

        if (result is Resource.Success) {
            //el producto vuelve a la caché local; los listados activos se actualizan por el Flow de Room
            localDataSource.insert(result.data.toProductEntity())
        }
        return result
    }

}