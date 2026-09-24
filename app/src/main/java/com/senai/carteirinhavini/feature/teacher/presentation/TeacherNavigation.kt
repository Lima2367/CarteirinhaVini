package com.senai.carteirinhavini.feature.teacher.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.senai.carteirinhavini.app.navigation.Routes

fun NavGraphBuilder.teacherScreens(navController: NavController) {
    composable(Routes.ProfessorHome) { ProfessorHomeScreen(navController) }
    composable(Routes.ProfessorTurmas) { ProfessorTurmasScreen(navController) }
    composable(
        Routes.ProfessorUnidades,
        arguments = listOf(navArgument("turma") { type = NavType.StringType })
    ) { entry ->
        ProfessorUnidadesScreen(
            navController,
            entry.arguments?.getString("turma") ?: "2º DEV A"
        )
    }
    composable(
        Routes.ProfessorNotas,
        arguments = listOf(
            navArgument("turma") { type = NavType.StringType },
            navArgument("unidade") { type = NavType.StringType }
        )
    ) { entry ->
        ProfessorNotasScreen(
            turma = entry.arguments?.getString("turma") ?: "2º DEV A",
            unidade = entry.arguments?.getString("unidade") ?: "PEND"
        )
    }
}
