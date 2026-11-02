package com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.update

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogo.klotinecommercemvvm.domain.model.Category
import com.dogo.klotinecommercemvvm.domain.useCase.categories.CategoriesUseCase
import com.dogo.klotinecommercemvvm.domain.util.Resource
import com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.create.mapper.toCategory
import com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.update.mapper.toCategory
import com.dogo.klotinecommercemvvm.presentation.util.ComposeFileProvider
import com.dogo.klotinecommercemvvm.presentation.util.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject



@HiltViewModel
class AdminCategoryUpdateViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val categoriesUseCase: CategoriesUseCase,
    @ApplicationContext val context: Context
): ViewModel() {


    var state by mutableStateOf(AdminCategoryUpdateState())
        private set

    var categoryResponse by mutableStateOf<Resource<Category>?> (null)
        private set

    var file: File? = null
    val resultingActivityHandler = ResultingActivityHandler()

    var data = savedStateHandle.get<String>("category")
    val category = Category.fromJson(data!!)

    init {
        state = state.copy(
            id = category.id ?: "",
            name = category.name,
            description = category.description,
            image = category.image!!
        )
    }

    fun onUpdate(){
        if (file != null){
            updateCategoryWithImage()
        }else{
            updateCategory()
        }
    }

    fun updateCategory() = viewModelScope.launch {
        categoryResponse = Resource.Loading
        val result = categoriesUseCase.updateCategory(category.id!!, state.toCategory())
        categoryResponse = result
    }


    fun updateCategoryWithImage() = viewModelScope.launch {
        categoryResponse = Resource.Loading
        val result = categoriesUseCase.updateCategoryWithImage(category.id!!, state.toCategory(), file!!)
        categoryResponse = result
    }






    //selecionar desde galeria
    fun pickImage() = viewModelScope.launch{
        val result = resultingActivityHandler.getContent("image/*")
        if(result!=null){
            file = ComposeFileProvider.createFileFromUri(context, result)
            state = state.copy(image = result.toString())

        }
    }
    //tomar foto
    fun takePhoto() = viewModelScope.launch {
        val result = resultingActivityHandler.takePicturePreview()
        if(result!=null){
            state = state.copy(image = ComposeFileProvider.getPathFromBitmap(context, result))
            file = File(state.image)
        }

    }

    fun onNameInput(input: String){
        state = state.copy(name = input)
    }
    fun onDescriptionInput(input: String){
        state = state.copy(description = input)
    }

}