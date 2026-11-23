package com.dogo.klotinecommercemvvm.presentation.screens.admin.product.update

import androidx.compose.runtime.mutableStateOf

data class AdminProductUpdateState(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val idCategory: String = "",
    val image1: String = "",
    val image2: String = "",
    val price: Double = 0.0,
    val imageToUpdate: MutableList<Int> = mutableListOf()
)
