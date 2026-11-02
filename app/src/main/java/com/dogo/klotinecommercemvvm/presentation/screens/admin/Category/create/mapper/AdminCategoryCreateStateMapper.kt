package com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.create.mapper

import com.dogo.klotinecommercemvvm.domain.model.Category
import com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.create.AdminCategoryCreateState

fun AdminCategoryCreateState.toCategory(): Category {
    return Category(
        name = name,
        description = description,
    )
}