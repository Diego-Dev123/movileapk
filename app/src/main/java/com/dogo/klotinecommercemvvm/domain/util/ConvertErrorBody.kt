package com.dogo.klotinecommercemvvm.domain.util

import android.util.Log
import com.dogo.klotinecommercemvvm.domain.model.ErrorResponse
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

object ConvertErrorBody {

    fun convert(errorBody: ResponseBody?): ErrorResponse? {

        return try {
            errorBody?.source()?.let {
                val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

                val moshiAdapter = moshi.adapter(ErrorResponse::class.java)

                moshiAdapter.fromJson(it)
            }

        } catch (e: Exception){
            Log.d("ConvertErrorBody", "Error: ${e.message}")
            null
        }
    }
}