package com.dogo.klotinecommercemvvm.domain.model

import com.google.gson.annotations.SerializedName


data class TrashedCategory(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String? = null,
    @SerializedName("image") val image: String? = null,
    @SerializedName("deleted_at") val deletedAt: String? = null
)
