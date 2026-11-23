package com.dogo.klotinecommercemvvm.presentation.screens.admin.product.create.mapper

import com.dogo.klotinecommercemvvm.domain.model.Product
import com.dogo.klotinecommercemvvm.presentation.screens.admin.product.create.AdminProductCreateState

fun AdminProductCreateState.toProduct(): Product{
    return Product(
        name = name,
        description = description,
        idCategory = idCategory,
        price = price,
    )
}
