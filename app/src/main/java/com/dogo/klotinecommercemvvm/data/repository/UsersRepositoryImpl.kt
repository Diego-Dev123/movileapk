package com.dogo.klotinecommercemvvm.data.repository

import com.dogo.klotinecommercemvvm.data.dataSource.remote.UsersRemoteDataSource
import com.dogo.klotinecommercemvvm.domain.model.User
import com.dogo.klotinecommercemvvm.domain.repository.UsersRepository
import com.dogo.klotinecommercemvvm.domain.util.Resource
import com.dogo.klotinecommercemvvm.domain.util.ResponseToRequest
import java.io.File


class UsersRepositoryImpl(
    private val usersRemoteDataSource: UsersRemoteDataSource

    ): UsersRepository {


    override suspend fun update(id: String,user: User): Resource<User> = ResponseToRequest.send(
        usersRemoteDataSource.update(id, user)

    )

    override suspend fun updatewithImage(id: String,user: User, file: File): Resource<User> = ResponseToRequest.send(
        usersRemoteDataSource.updatewithImage(id, user, file)

    )


}