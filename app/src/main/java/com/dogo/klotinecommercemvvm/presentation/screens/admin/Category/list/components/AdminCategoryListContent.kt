package com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.list.components


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.dogo.klotinecommercemvvm.domain.model.Category



@Composable
fun AdminCategoryListContent(navController: NavHostController,paddingValues: PaddingValues,categories: List<Category>){


    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(paddingValues = paddingValues)
    ) {
        items(
            items = categories
        ){
            category ->
            AdminCategoryListItem(navController,category)
        }
    }
}