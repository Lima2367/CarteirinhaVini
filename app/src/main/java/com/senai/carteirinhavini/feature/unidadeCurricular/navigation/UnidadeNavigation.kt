package com.senai.carteirinhavini.feature.unidadeCurricular.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.senai.carteirinhavini.app.navigation.Routes
import com.senai.carteirinhavini.feature.unidadeCurricular.presentation.screen.UnidadeCurricularScreen

fun NavGraphBuilder.unidadeCurricularScreen(
    onNavigateToLogin: () -> Unit
) {
    composable (Routes.UnidadeCurricular) {
        UnidadeCurricularScreen(
        )
    }
}