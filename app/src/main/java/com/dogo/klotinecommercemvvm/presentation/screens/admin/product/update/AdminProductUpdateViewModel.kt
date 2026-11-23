package com.dogo.klotinecommercemvvm.presentation.screens.admin.product.update

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogo.klotinecommercemvvm.domain.model.Category
import com.dogo.klotinecommercemvvm.domain.model.Product
import com.dogo.klotinecommercemvvm.domain.useCase.products.ProductsUseCase
import com.dogo.klotinecommercemvvm.domain.util.Resource
import com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.create.AdminCategoryCreateState
import com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.create.mapper.toCategory
import com.dogo.klotinecommercemvvm.presentation.screens.admin.product.create.AdminProductCreateState
import com.dogo.klotinecommercemvvm.presentation.screens.admin.product.create.mapper.toProduct
import com.dogo.klotinecommercemvvm.presentation.screens.admin.product.update.mapper.toProduct
import com.dogo.klotinecommercemvvm.presentation.util.ComposeFileProvider
import com.dogo.klotinecommercemvvm.presentation.util.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AdminProductUpdateViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val savedStateHandle: SavedStateHandle,
    private val productsUseCase: ProductsUseCase
): ViewModel() {


    var state by mutableStateOf(AdminProductUpdateState())
        private set

    var productResponse by mutableStateOf<Resource<Product>?> (null)
        private set

    var data = savedStateHandle.get<String>("product")
    var product = Product.fromJson(data?:"")

    //imagenes
    var file1: File? = null
    var file2: File? = null
    var files: MutableList<File> = mutableListOf()
    val resultingActivityHandler = ResultingActivityHandler()

    init {
        state = state.copy(
            name = product.name,
            description = product.description,
            image1 = product.image1?:"",
            image2 = product.image2?:"",
            price = product.price,
            idCategory = product.idCategory)
    }

    //upd

    fun updateProduct() = viewModelScope.launch {

        if (file1 == null && file2 == null) {
            productResponse = Resource.Loading

            val result = productsUseCase.updateProduct(
                product.id!!,
                state.toProduct()
            )

            productResponse = result

        } else {

            val selectedFiles = mutableListOf<File>()
            val imagePositions = mutableListOf<Int>()

            if (file1 != null) {
                selectedFiles.add(file1!!)
                imagePositions.add(1)
            }

            if (file2 != null) {
                selectedFiles.add(file2!!)
                imagePositions.add(2)
            }

            val productToUpdate = state.toProduct().copy(
                imageToUpdate = imagePositions
            )

            productResponse = Resource.Loading

            val result = productsUseCase.updateWithImageProduct(
                product.id!!,
                productToUpdate,
                selectedFiles
            )

            productResponse = result
        }
        files.clear()
        file1 = null
        file2 = null
        state.imageToUpdate.clear()
    }




    //selecionar desde galeria
    fun pickImage(imageNumber: Int) = viewModelScope.launch{
        val result = resultingActivityHandler.getContent("image/*")
        if(result!=null){
            if(imageNumber == 1){
                file1 = ComposeFileProvider.createFileFromUri(context, result)
                state = state.copy(image1 = result.toString())
//                files.add(file1!!)
            } else if(imageNumber == 2){
                file2 = ComposeFileProvider.createFileFromUri(context, result)
                state = state.copy(image2 = result.toString())
//                files.add(file2!!)
            }
        }
    }
    //tomar foto
    fun takePhoto(imageNumber: Int) = viewModelScope.launch {
        val result = resultingActivityHandler.takePicturePreview()
        if(result!=null){
            if(imageNumber == 1){
                state = state.copy(image1 = ComposeFileProvider.getPathFromBitmap(context, result))
                file1 = File(state.image1)
//                files.add(file1!!)
            } else if(imageNumber == 2){
                state = state.copy(image2 = ComposeFileProvider.getPathFromBitmap(context, result))
                file2 = File(state.image2)
//                files.add(file2!!)
            }

        }

    }

    fun onNameInput(input: String){
        state = state.copy(name = input)
    }
    fun onDescriptionInput(input: String){
        state = state.copy(description = input)
    }
    fun onPriceInput(input: String){
        state = state.copy(price = input.toDouble())
    }

}