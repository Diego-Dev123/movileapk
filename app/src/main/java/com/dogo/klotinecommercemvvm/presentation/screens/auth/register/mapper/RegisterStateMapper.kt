package com.dogo.klotinecommercemvvm.presentation.screens.auth.register.mapper

import com.dogo.klotinecommercemvvm.domain.model.User
import com.dogo.klotinecommercemvvm.presentation.screens.auth.register.RegisterState

fun RegisterState.toUser(): User {
    return User(
        name = name,
        lastname = lastName,
        email = email,
        phone = phone,
        password = password
    )
}