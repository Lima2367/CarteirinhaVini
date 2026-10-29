package com.senai.carteirinhavini.feature.unidadecurriculares.presentation

import com.senai.carteirinhavini.feature.unidadecurriculares.domain.model.UnidadeCurricular

data class UnidadeCurricularUiState(
    val isLoading: Boolean = false,
    val unidades: List<UnidadeCurricular> = emptyList(),
    val errorMessage: String? = null
)