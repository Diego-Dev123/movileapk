package com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.update.mapper

import com.dogo.klotinecommercemvvm.domain.model.Category
import com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.create.AdminCategoryCreateState
import com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.update.AdminCategoryUpdateState

fun AdminCategoryUpdateState.toCategory(): Category {
    return Category(
        id = id,
        name = name,
        description = description,
    )
}