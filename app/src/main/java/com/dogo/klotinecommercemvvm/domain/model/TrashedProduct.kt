package com.dogo.klotinecommercemvvm.domain.model

import com.google.gson.annotations.SerializedName


data class TrashedProduct(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String? = null,
    @SerializedName("price") val price: Double = 0.0,
    @SerializedName("image1") val image1: String? = null,
    @SerializedName("image2") val image2: String? = null,
    @SerializedName("id_category") val idCategory: String? = null,
    @SerializedName("deleted_at") val deletedAt: String? = null
)
