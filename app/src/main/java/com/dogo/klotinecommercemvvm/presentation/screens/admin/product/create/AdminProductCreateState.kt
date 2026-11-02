package com.dogo.klotinecommercemvvm.presentation.screens.admin.product.create

data class AdminProductCreateState(
    val name: String = "",
    val description: String = "",
    val idCategory: String = "",
    val image1: String = "",
    val image2: String = "",
    val price: Double = 0.0
)
