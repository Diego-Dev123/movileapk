package com.dogo.klotinecommercemvvm.data.dataSource.remote.service

import com.dogo.klotinecommercemvvm.domain.model.AuthResponse
import com.dogo.klotinecommercemvvm.domain.model.LoginRequest
import com.dogo.klotinecommercemvvm.domain.model.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface UsersService {

    @PUT("users/update/{id}")
    suspend fun update(
        @Path("id") id: String,
        @Body() user: User): Response<User>




    @Multipart
    @PUT("users/{id}")
    suspend fun updatewithImage(
        @Part() file: MultipartBody.Part,
        @Path("id") id: String,
        @Part("name") name: RequestBody,
        @Part("lastname") lastname: RequestBody,
        @Part("phone") phone: RequestBody,

    ): Response<User>


}


