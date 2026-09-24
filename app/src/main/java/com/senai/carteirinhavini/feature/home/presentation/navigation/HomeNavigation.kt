package com.senai.carteirinhavini.feature.home.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.senai.carteirinhavini.app.navigation.Routes
import com.senai.carteirinhavini.feature.auth.domain.model.UsuarioLogado
import com.senai.carteirinhavini.feature.home.presentation.screen.HomeScreen

fun NavGraphBuilder.homeScreen(navController: NavController) {
    composable(Routes.Home) {
        HomeScreen(
            navController = navController,
            usuarioLogado = UsuarioLogado(
                id = "12-3",
                nome = "Lima",
                curso = "Desenvolvimento de Sistema",
                turma = "2DEVEST-A",
                token = "token-fake-vindo-do-sistema"
            )
        )
    }
}
