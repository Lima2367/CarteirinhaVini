package com.senai.carteirinhavini.feature.professor.unidadescurriculares.presentation

import com.senai.carteirinhavini.feature.professor.unidadescurriculares.domain.model.UnidadeCurricularProfessor

data class UnidadesCurricularesProfessorUiState(
    val isLoading: Boolean = false,
    val unidades: List<UnidadeCurricularProfessor> = emptyList(),
    val errorMessage: String? = null
)
