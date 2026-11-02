package com.dogo.klotinecommercemvvm.presentation.screens.profile.update

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogo.klotinecommercemvvm.domain.model.User
import com.dogo.klotinecommercemvvm.domain.useCase.auth.AuthUseCase
import com.dogo.klotinecommercemvvm.domain.useCase.users.UsersUseCase
import com.dogo.klotinecommercemvvm.domain.util.Resource
import com.dogo.klotinecommercemvvm.presentation.screens.profile.update.mapper.toUser
import com.dogo.klotinecommercemvvm.presentation.util.ComposeFileProvider
import com.dogo.klotinecommercemvvm.presentation.util.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject


@HiltViewModel
class ProfileUpdateViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val usersUseCase: UsersUseCase,
    private val saveStateHandle: SavedStateHandle,
    @ApplicationContext private val context: Context

): ViewModel() {

    var state by mutableStateOf(ProfileUpdateState())


    //argumentos
    val data = saveStateHandle.get<String>("user")
    var user = User.fromJson(data!!)


    var updateResponse by mutableStateOf<Resource<User>?>(null)
        private set



    //imagenes
    var file: File? = null
    val resultingActivityHandler = ResultingActivityHandler()


    init {
        state = state.copy(
            name = user.name,
            lastName = user.lastname,
            phone = user.phone,
            image = user.image ?: ""
        )
        Log.d("ProfileUpdateVM", "imagen: '${user.image}'")
    }

    fun updateUserSessionData(userResponse: User) = viewModelScope.launch {
        authUseCase.updateSession(userResponse)
    }

    fun onUpdate(){
        if(file != null){
            updateWithImage()
        } else{
            update()
        }
    }


    fun updateWithImage() = viewModelScope.launch {
        updateResponse = Resource.Loading
        val result = usersUseCase.updateUserWithImage(user.id?.toString() ?:"", state.toUser(), file!!)
        updateResponse = result
    }


    fun update() = viewModelScope.launch {
        Log.d("ProfileU", "enviando: name=${state.name}, lastname=${state.lastName}, phone=${state.phone}")

        updateResponse = Resource.Loading
        val result = usersUseCase.updateUser(user.id?.toString() ?:"", state.toUser())
        updateResponse = result

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
    fun onLastNameInput(input: String){
        state = state.copy(lastName = input)
    }

    fun onPhoneInput(input: String){
        state = state.copy(phone = input)
    }
    fun onImageInput(input: String){
        state = state.copy(image = input)
    }

}