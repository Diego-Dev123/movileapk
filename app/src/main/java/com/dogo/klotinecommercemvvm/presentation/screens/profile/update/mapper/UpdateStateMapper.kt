package com.dogo.klotinecommercemvvm.presentation.screens.profile.update.mapper

import com.dogo.klotinecommercemvvm.domain.model.User
import com.dogo.klotinecommercemvvm.presentation.screens.profile.update.ProfileUpdateState

fun ProfileUpdateState.toUser(): User{
    return User(
        name = name,
        lastname = lastName,
        phone = phone,
        image = image
    )
}