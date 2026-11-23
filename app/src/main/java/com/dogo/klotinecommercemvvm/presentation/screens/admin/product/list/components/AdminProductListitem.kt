package com.dogo.klotinecommercemvvm.presentation.screens.admin.product.list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.dogo.klotinecommercemvvm.domain.model.Product
import com.dogo.klotinecommercemvvm.presentation.components.CardActionButton
import com.dogo.klotinecommercemvvm.presentation.components.ItemCard
import androidx.compose.ui.graphics.Color
import com.dogo.klotinecommercemvvm.presentation.navigation.screen.admin.AdminCategoryScreen

@Composable
fun AdminProductListItem(navController: NavHostController, product: Product, onDelete: (Product) -> Unit){
    ItemCard(
        image = product.image1,
        title = product.name,
        description = product.description,
        price = product.price.toString(),
        imageOnSide = true,
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
                        navController.navigate(route = AdminCategoryScreen.ProductUpdate.passProduct(product.toJson()))
                    }
                )
                //eliminar
                CardActionButton(
                    iconRes = R.drawable.trash,
                    tint = Color(0xFFDC2626),
                    onClick = {
                        onDelete(product)
                    }
                )
            }
        }
    )
}
