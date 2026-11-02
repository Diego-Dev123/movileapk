package com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.update

import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dogo.klotinecommercemvvm.presentation.components.DefaultTopBar
import com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.create.components.AdminCategoryCreateContent
import com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.create.components.CreateCategory
import com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.update.components.AdminCategoryUpdateContent
import com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.update.components.UpdateCategory
import com.dogo.klotinecommercemvvm.ui.theme.Gris200


@Composable
fun AdminCategoryUpdateScreen(
    navController: NavHostController,
    categoryParam: String,
    vm: AdminCategoryUpdateViewModel = hiltViewModel()
){

    Log.d("AdminCategoryUpdateScreen", "AdminCategoryUpdateScreen: $categoryParam")

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Actualizar Categoria",
                upAvailable = true,
                navController = navController
            )
        },
        containerColor = Gris200
    ) { paddingValues ->
        AdminCategoryUpdateContent(paddingValues = paddingValues, vm = vm)
    }
    UpdateCategory()
}