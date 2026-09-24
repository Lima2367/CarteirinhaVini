package com.senai.carteirinhavini.feature.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.senai.carteirinhavini.app.navigation.Routes
import com.senai.carteirinhavini.feature.auth.presentation.Screen.LoginScreen

fun NavGraphBuilder.authScreen(navController: NavController) {
    composable(Routes.Login) {
        LoginScreen(
            onLoginSucesso = {
                navController.navigate(Routes.Home) {
                    popUpTo(Routes.Login) { inclusive = true }
                }
            }
        )
    }
}
