package com.dogo.klotinecommercemvvm.data.dataSource.remote

import com.dogo.klotinecommercemvvm.data.dataSource.remote.service.UsersService
import com.dogo.klotinecommercemvvm.domain.model.User
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.File

class UsersRemoteDataSourceImpl(private val usersService: UsersService): UsersRemoteDataSource {

    override suspend fun update(id: String, user: User): Response<User> = usersService.update(id, user)
    override suspend fun updatewithImage(
        id: String,
        user: User,
        file: File
    ): Response<User> {
        val conecction = file.toURI().toURL().openConnection()
        val mimetype = conecction.contentType //saber si es jpg png etc
        val conentType = "text/plain"
        val requestFile = file.asRequestBody(mimetype.toMediaTypeOrNull())
        val fileFormData = MultipartBody.Part.createFormData("file", file.name, requestFile)
        val nameData = user.name.toRequestBody(conentType.toMediaTypeOrNull())
        val lastnameData = user.lastname.toRequestBody(conentType.toMediaTypeOrNull())
        val phoneData = user.phone.toRequestBody(conentType.toMediaTypeOrNull())

        return usersService.updatewithImage(fileFormData, id, nameData, lastnameData, phoneData)

    }

}