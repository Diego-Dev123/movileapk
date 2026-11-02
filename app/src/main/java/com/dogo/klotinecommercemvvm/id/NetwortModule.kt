package com.dogo.klotinecommercemvvm.id

import android.util.Log
import com.dogo.klotinecommercemvvm.core.Config.BASE_URL
import com.dogo.klotinecommercemvvm.data.dataSource.local.dataStore.AuthDataStore
import com.dogo.klotinecommercemvvm.data.dataSource.remote.service.AuthService
import com.dogo.klotinecommercemvvm.data.dataSource.remote.service.CategoriesService
import com.dogo.klotinecommercemvvm.data.dataSource.remote.service.UsersService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(dataStore: AuthDataStore) = OkHttpClient.Builder().addInterceptor {
        val token = runBlocking {
            dataStore.getData().first().token
        }
        Log.d("TOKEN_DEBUG", "Token enviado: '$token'")
        val newRequest = it.request().newBuilder().addHeader("Authorization", token ?: "").build()
        it.proceed(newRequest)
    }.build()


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providAuthService(retrofit: Retrofit): AuthService{
        return retrofit.create(AuthService::class.java)
    }

    @Provides
    @Singleton
    fun providUsersService(retrofit: Retrofit): UsersService {
        return retrofit.create(UsersService::class.java)
    }

    @Provides
    @Singleton
    fun provideCategoryService(retrofit: Retrofit): CategoriesService {
        return retrofit.create(CategoriesService::class.java)
    }
}