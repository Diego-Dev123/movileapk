package com.dogo.klotinecommercemvvm.presentation.screens.client.category.list.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dogo.klotinecommercemvvm.domain.model.Category


@Composable
fun ClientCategoryListContent(
    paddingValues: PaddingValues,
    navControlller: NavHostController,
    categories: List<Category>)
{
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues= paddingValues)
            .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 55.dp)
    ) {
        items(
            items = categories
        ){
            ClientCategoryListItem(
                navController = navControlller,
                category = it
            )
        }
    }
}