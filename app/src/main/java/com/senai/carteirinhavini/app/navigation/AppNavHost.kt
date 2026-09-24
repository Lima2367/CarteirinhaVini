package com.senai.carteirinhavini.app.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.senai.carteirinhavini.app.session.SessionViewModel
import com.senai.carteirinhavini.feature.auth.presentation.Screen.LoginScreen
import com.senai.carteirinhavini.feature.carteirinha.presentation.CarteirinhaScreen
import com.senai.carteirinhavini.feature.home.presentation.screen.HomeScreen
import com.senai.carteirinhavini.feature.unidadeCurricular.presentation.screen.UnidadeCurricularScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    sessionViewModel: SessionViewModel = viewModel()
) {
    val usuarioLogado by sessionViewModel.usuarioLogado.collectAsStateWithLifecycle()
    val usuario = usuarioLogado

    NavHost(
        navController = navController,
        startDestination = Routes.Login
    ) {
        composable(Routes.Login) {
            LoginScreen(
                onLoginSucesso = { usuarioLogado ->
                    sessionViewModel.setUsuarioLogado(usuarioLogado)
                    navController.navigate(Routes.Home) {
                        popUpTo(Routes.Login) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.Home) {
            if (usuario == null) {
                LaunchedEffect(Unit) {
                    navController.navigate(Routes.Login) {
                        popUpTo(Routes.Home) { inclusive = true }
                    }
                }
            } else {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),
                        usuarioLogado = usuario
                    )
                }
            }
        }

        composable(Routes.Carteirinha) {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                CarteirinhaScreen(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

        composable(Routes.UnidadeCurricular) {
            if (usuario == null) {
                LaunchedEffect(Unit) {
                    navController.navigate(Routes.Login) {
                        popUpTo(Routes.UnidadeCurricular) { inclusive = true }
                    }
                }
            } else {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnidadeCurricularScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
