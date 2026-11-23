package com.dogo.klotinecommercemvvm.presentation.screens.client.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.dogo.klotinecommercemvvm.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClientProductDetailViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle): ViewModel() {

    var data = savedStateHandle.get<String>("product")

    var product = Product.fromJson(data!!)

    var productImages = listOf<String>(product.image1 ?:"",product.image2 ?:"")
}