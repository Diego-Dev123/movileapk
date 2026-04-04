package com.dogo.klotinecommercemvvm.domain.model

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ErrorMessage(
    val statusCode: Int = 500,
    val message: String = ""
)
