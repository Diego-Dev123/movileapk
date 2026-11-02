package com.dogo.klotinecommercemvvm.presentation.navigation.graph.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.dogo.klotinecommercemvvm.presentation.navigation.Graph
import com.dogo.klotinecommercemvvm.presentation.navigation.screen.profile.ProfileScreen
import com.dogo.klotinecommercemvvm.presentation.navigation.screen.roles.RolesScreen
import com.dogo.klotinecommercemvvm.presentation.screens.admin.home.AdminHomeScreen
import com.dogo.klotinecommercemvvm.presentation.screens.client.home.ClientHomeScreen
import com.dogo.klotinecommercemvvm.presentation.screens.profile.update.ProfileUpdateScreen
import com.dogo.klotinecommercemvvm.presentation.screens.roles.RolesScreen

fun NavGraphBuilder.ProfileNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.PROFILE + "/{user}",
        startDestination = ProfileScreen.profileUpdate.route + "/{user}" // 👈
    ) {
        composable(
            route = ProfileScreen.profileUpdate.route + "/{user}", // 👈
            arguments = listOf(navArgument("user") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("user")?.let { user ->
                ProfileUpdateScreen(navController, userParam = user)
            }
        }
    }
}