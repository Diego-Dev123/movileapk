package com.dogo.klotinecommercemvvm.presentation.screens.roles.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.dogo.klotinecommercemvvm.domain.model.Rol
import com.dogo.klotinecommercemvvm.presentation.navigation.Graph
import com.dogo.klotinecommercemvvm.presentation.navigation.screen.auth.AuthScreen

@Composable
fun RolesItem(rol: Rol, navController: NavHostController){
    Column(
        modifier = Modifier.clickable {
            navController.navigate(route = rol.route) {
                popUpTo(route = Graph.ROLES)
            }
        }
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Log.d("IMAGE_DEBUG", "URL: ${rol.image}")
        AsyncImage(
            modifier = Modifier.height(150.dp).width(150.dp),
            model = rol.image,
            contentDescription =""
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = rol.name,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        )
    }

}