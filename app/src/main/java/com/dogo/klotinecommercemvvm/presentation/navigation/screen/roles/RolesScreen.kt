package com.dogo.klotinecommercemvvm.presentation.navigation.screen.roles

sealed class RolesScreen(val route: String) {
    object Home: RolesScreen("home")
    object Roles: RolesScreen("roles")

}