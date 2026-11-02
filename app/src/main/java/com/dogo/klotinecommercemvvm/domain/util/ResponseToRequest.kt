package com.dogo.klotinecommercemvvm.domain.util

import com.dogo.klotinecommercemvvm.domain.model.ErrorResponse
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response


object ResponseToRequest {
    fun <T> send(result: Response<T>): Resource<T>{
        return try {
            if(result.isSuccessful){
                Resource.Success(result.body()!!)
            } else{
                val errorResponse: ErrorResponse? = ConvertErrorBody.convert(result.errorBody())
                Resource.Failure(errorResponse?.message ?:"Error desconocido")
            }


        }catch (e: HttpException){
            e.printStackTrace()
            Resource.Failure(e.message ?:"Error Desconocido en la peticion http")
        }catch (e: IOException){
            e.printStackTrace()
            Resource.Failure(e.message ?:"Verifica conexion a internet")
        }catch (e: Exception){
            e.printStackTrace()
            Resource.Failure(e.message ?:"Error Desconocido")
        }
    }
}