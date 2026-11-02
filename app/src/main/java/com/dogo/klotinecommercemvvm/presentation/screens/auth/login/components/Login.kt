package com.dogo.klotinecommercemvvm.presentation.screens.auth.login.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dogo.klotinecommercemvvm.domain.util.Resource
import com.dogo.klotinecommercemvvm.presentation.components.ProgressBar
import com.dogo.klotinecommercemvvm.presentation.navigation.Graph
import com.dogo.klotinecommercemvvm.presentation.navigation.screen.auth.AuthScreen
import com.dogo.klotinecommercemvvm.presentation.screens.auth.login.LoginViewModel



@Composable
fun Login(navController: NavHostController,vm: LoginViewModel= hiltViewModel()){
    when(val response = vm.loginResponse){

        //retornar cuando la repsusta este cargando
        Resource.Loading ->{
            ProgressBar()
        }

        //cuando la respuesta es exitosa
        is Resource.Success -> {
            LaunchedEffect(Unit) {

                vm.saveSession(response.data)

                if(response.data.user?.roles!!.size > 1){ // cuando el user tiene mas de 1 rol
                    navController.navigate(route = Graph.ROLES){
                        popUpTo(Graph.AUTH){
                            inclusive = true
                        }
                    }
                } else{ //un solo rol
                    navController.navigate(Graph.CLIENT){
                        popUpTo(Graph.AUTH){
                            inclusive = true
                        }
                    }
                }

            }
        }

        //cuando falla
        is Resource.Failure ->{
            Toast.makeText(LocalContext.current, response.message, Toast.LENGTH_SHORT).show()
        }


        //else por si hay un error desconocido
        else -> {
            if(response != null){
                Toast.makeText(LocalContext.current, "Error desconocido", Toast.LENGTH_SHORT).show()
            }
        }


    }
}




//@Composable
//fun Login(navController: NavHostController, vm: LoginViewModel = hiltViewModel()) {
//    val context = LocalContext.current
//
//    when (val response = vm.loginResponse) {
//
//        Resource.Loading -> {
//            ProgressBar()
//        }
//
//        is Resource.Success -> {
//            LaunchedEffect(Unit) {
//                val data = response.data
//                if (data == null) {
//                    Toast.makeText(context, "Error: respuesta vacía", Toast.LENGTH_SHORT).show()
//                    return@LaunchedEffect
//                }
//
//                vm.saveSession(data)
//
//                val roles = data.user?.roles ?: emptyList()
//                val destination = if (roles.size > 1) {
//                    AuthScreen.Roles.route
//                } else {
//                    AuthScreen.Home.route
//                }
//
//                navController.navigate(destination) {
//                    popUpTo(AuthScreen.Login.route) { inclusive = true }
//                }
//            }
//        }
//
//        is Resource.Failure -> {
//            Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
//        }
//
//        else -> {
//            if (response != null) {
//                Toast.makeText(context, "Error desconocido", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//}
