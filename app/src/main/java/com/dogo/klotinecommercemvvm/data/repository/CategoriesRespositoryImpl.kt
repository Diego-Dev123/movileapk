package com.dogo.klotinecommercemvvm.data.repository

import android.util.Log
import com.dogo.klotinecommercemvvm.data.dataSource.local.CategoriesLocalDataSource
import com.dogo.klotinecommercemvvm.data.dataSource.local.entity.CategoryEntity
import com.dogo.klotinecommercemvvm.data.dataSource.remote.CategoryRemoteDataSource
import com.dogo.klotinecommercemvvm.data.mapper.toCategory
import com.dogo.klotinecommercemvvm.data.mapper.toCategoryEntity
import com.dogo.klotinecommercemvvm.domain.model.Category
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
    private val localDataSource: CategoriesLocalDataSource
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
        localDataSource.getCategories().collect {
            it.run {

//                if (this.isNotEmpty()) {
//                    Log.d("CategoriesRespositoryImpl", "data local: $this")
//                    emit(Resource.Success(this.map { categoryEntity -> categoryEntity.toCategory() }))
//                }
                val categoriesLocalMap = this.map {
                        categoryEntity -> categoryEntity.toCategory()
                }

                try {
                    ResponseToRequest.send(remoteDataSource.getCategories()).run {
                        when (this) {
                            is Resource.Success -> {
                                val categoriesRemote = this.data
                                if (!isListEqual(categoriesRemote, categoriesLocalMap)){
                                    localDataSource.insertAll(categoriesRemote.map { category -> category.toCategoryEntity() })
                                }

                                emit(Resource.Success(categoriesRemote))
                            }
                            is Resource.Failure ->{
                                emit(Resource.Success(categoriesLocalMap))
                            }
                            else -> {
                                emit(Resource.Success(categoriesLocalMap))
                            }
                        }
                    }

                }catch (e: Exception){
                    emit(Resource.Success(categoriesLocalMap))
                }

            }
        }

    }.flowOn(Dispatchers.IO)





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
        ResponseToRequest.send(remoteDataSource.delete(id)).run {
            return when(this){
                is Resource.Success ->{
                    localDataSource.delete(id)
                    Resource.Success(Unit)
                }
                else-> {
                    Resource.Failure("Error Desconocido")
                }
            }
        }
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