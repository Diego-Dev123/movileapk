package com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dogo.klotinecommercemvvm.R
import com.dogo.klotinecommercemvvm.domain.model.Category
import com.dogo.klotinecommercemvvm.presentation.components.CardActionButton
import com.dogo.klotinecommercemvvm.presentation.components.ItemCard
import androidx.compose.ui.graphics.Color
import com.dogo.klotinecommercemvvm.presentation.navigation.screen.admin.AdminCategoryScreen

@Composable
fun AdminCategoryListItem(navController: NavHostController, category: Category, onDelete: (Category) -> Unit){
    ItemCard(
        image = category.image,
        title = category.name,
        description = category.description,
        onClick = {
            navController.navigate(route = AdminCategoryScreen.ProductList.passCategory(category.toJson()))
        },
        actions = {
            Column(
                modifier = Modifier.padding(start = 12.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                //editar
                CardActionButton(
                    iconRes = R.drawable.edit,
                    tint = Color(0xFF4F46E5),
                    onClick = {
                        navController.navigate(route = AdminCategoryScreen.CategoryUpdate.passCategory(category.toJson()))
                    }
                )
                //eliminar
                CardActionButton(
                    iconRes = R.drawable.trash,
                    tint = Color(0xFFDC2626),
                    onClick = {
                        onDelete(category)
                    }
                )
            }
        }
    )
}
