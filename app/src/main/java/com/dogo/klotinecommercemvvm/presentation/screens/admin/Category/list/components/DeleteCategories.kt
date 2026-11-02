package com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.list.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dogo.klotinecommercemvvm.domain.util.Resource
import com.dogo.klotinecommercemvvm.presentation.components.ProgressBar
import com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.list.AdminCategoryListViewModel


@Composable
fun DeleteCategory(vm: AdminCategoryListViewModel = hiltViewModel()){
    when(val response = vm.deleteCategoryResponse){

        //retornar cuando la repsusta este cargando
        Resource.Loading ->{
            ProgressBar()
        }

        //cuando la respuesta es exitosa
        is Resource.Success -> {
            Log.d("DeleteCategories", "Data: ${response.data}")

            Toast.makeText(LocalContext.current, "Categoria Eliminada", Toast.LENGTH_SHORT).show()
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