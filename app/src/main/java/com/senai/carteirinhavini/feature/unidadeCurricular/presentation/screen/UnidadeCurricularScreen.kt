package com.senai.carteirinhavini.feature.unidadeCurricular.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.senai.carteirinhavini.feature.unidadeCurricular.data.dataSource

@Composable
fun UnidadeCurricularScreen(
    modifier: Modifier = Modifier
) {
    val unidadesCurriculares = dataSource()

    UnidadeCurricularContent(
        unidadesCurriculares = unidadesCurriculares
    )
}