package com.senai.carteirinhavini.feature.academic.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.senai.carteirinhavini.app.navigation.Routes

fun NavGraphBuilder.academicScreens(navController: NavController) {
    composable(Routes.Semestres) {
        SemestresScreen(navController)
    }
    composable(
        route = Routes.NotasPresenca,
        arguments = listOf(navArgument("semestre") { type = NavType.IntType })
    ) { entry ->
        NotasPresencaScreen(
            navController = navController,
            semestre = entry.arguments?.getInt("semestre") ?: 1
        )
    }
}
