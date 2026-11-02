package com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.dogo.klotinecommercemvvm.R
import com.dogo.klotinecommercemvvm.domain.model.Category
import com.dogo.klotinecommercemvvm.presentation.navigation.screen.admin.AdminCategoryScreen
import com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.list.AdminCategoryListViewModel

@Composable
fun AdminCategoryListItem(navController: NavHostController,category: Category, vm: AdminCategoryListViewModel = hiltViewModel()){

    Column(
        Modifier
            .padding(start = 20.dp, end = 20.dp, top = 15.dp)
            .height(90.dp)
            .clickable {
                navController.navigate(route = AdminCategoryScreen.ProductList.passCategory(category.toJson()))
            }
    ) {



        Row() {
            AsyncImage(
                modifier = Modifier.size(70.dp).clip(RoundedCornerShape(10.dp)),
                model = category.image,
                contentDescription = "",
            )
            Spacer(modifier = Modifier.width(15.dp))
            Column(
                Modifier.weight(1f)
            ) {
                //nombre de la categoria
                Text(
                    text = category.name,
                    color = Color.Black,
                    fontSize = 17.sp
                )

                Spacer(modifier = Modifier.height(5.dp))
                //descripcion de la cateogria
                Text(
                    text = category.description,
                    color = Color.Gray,
                    fontSize = 14.sp
                )

            }

            Spacer(modifier = Modifier.width(15.dp))
            //logos de editar y eliminar
            Column(
                verticalArrangement = Arrangement.Center
            ) {

                //imagen de editar
                Image(
                    modifier = Modifier
                        .size(25.dp)
                        .clickable{
                            navController.navigate(route = AdminCategoryScreen.CategoryUpdate.passCategory(category.toJson()))
                        },
                    painter = painterResource(id = R.drawable.edit),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.height(5.dp))
                //imagen de eliminar
                Image(
                    modifier = Modifier.size(25.dp).clickable {
                        vm.deleteCategories(category.id!!)
                    },
                    painter = painterResource(id = R.drawable.trash),
                    contentDescription = ""
                )
            }


        }
        Spacer(modifier = Modifier.height(10.dp))
        Divider(
            modifier = Modifier.padding(start = 30.dp),
            color = Color.LightGray
        )

    }
}