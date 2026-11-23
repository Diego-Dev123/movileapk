package com.dogo.klotinecommercemvvm.domain.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class Product(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name")val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("price")val price: Double,
    @SerializedName("image1")val image1: String? = null,
    @SerializedName("image2")val image2: String? = null,
    @SerializedName("id_category")val idCategory: String,
    @SerializedName("imageToUpdate[]")val imageToUpdate: List<Int>? = listOf()

): Serializable {
    fun toJson(): String = Gson().toJson(
        Product(
            id,
            name,
            description,
            price,
            if (!image1.isNullOrBlank()) {
                URLEncoder.encode(image1, StandardCharsets.UTF_8.toString())
            } else {
                ""
            },
            if (!image2.isNullOrBlank()) {
                URLEncoder.encode(image2, StandardCharsets.UTF_8.toString())
            } else {
                ""
            },
            idCategory,
            imageToUpdate
        )
    )

    companion object {
        fun fromJson(data: String): Product = Gson().fromJson(data, Product::class.java)
    }
}
