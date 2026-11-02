package com.dogo.klotinecommercemvvm.domain.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class User(
    @SerializedName("id") val id: Long? = 0,
    @SerializedName(value = "name") var name: String,
    @SerializedName(value = "lastname") var lastname: String,
    @SerializedName(value = "email") val email: String? = null,
    @SerializedName(value = "phone") var phone: String,
    @SerializedName(value = "password") val password: String? = null,
    @SerializedName(value = "image") var image: String? = null,
    @SerializedName(value = "notification_token") val notificationToken: String? = null,
    @SerializedName(value = "roles") val roles: List<Rol>? = null
){
    fun toJson(): String  = Gson().toJson(User(
        id,
        name,
        lastname,
        email,
        phone,
        password,
        if (!image.isNullOrBlank()) {
            URLEncoder.encode(image, StandardCharsets.UTF_8.toString())
        } else {
            ""
        },
        notificationToken,
        roles?.map { rol ->
            Rol.fromJson(rol.toJson())
        }
    ))

    companion object {
        fun fromJson(data: String): User = Gson().fromJson(data, User::class.java)
    }
}
