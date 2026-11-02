package com.dogo.klotinecommercemvvm.presentation.screens.admin.product.create

import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dogo.klotinecommercemvvm.presentation.components.DefaultTopBar
import com.dogo.klotinecommercemvvm.presentation.screens.admin.product.create.components.AdminProductCreateContent
import com.dogo.klotinecommercemvvm.ui.theme.Gris200

@Composable
fun AdminProductCreateScreen(navController: NavHostController, categoryParam : String){

    Log.d("AdminProductListScreen", "category: $categoryParam")

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "nuevo producto",
                upAvailable = true,
                navController = navController
            )
        },
        containerColor = Gris200
    ) {paddingValues ->
        AdminProductCreateContent(paddingValues = paddingValues)
    }

}