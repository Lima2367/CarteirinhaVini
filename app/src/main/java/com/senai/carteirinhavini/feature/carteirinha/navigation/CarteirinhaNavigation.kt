package com.senai.carteirinhavini.feature.carteirinha.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.senai.carteirinhavini.app.navigation.Routes
import com.senai.carteirinhavini.feature.carteirinha.presentation.CarteirinhaScreen

fun NavGraphBuilder.carteirinhaScreen(navController: NavController) {
    composable(Routes.Carteirinha) {
        CarteirinhaScreen()
    }
}
