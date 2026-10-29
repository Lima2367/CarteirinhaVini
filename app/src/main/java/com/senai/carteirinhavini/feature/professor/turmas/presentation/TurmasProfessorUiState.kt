package com.senai.carteirinhavini.feature.professor.turmas.presentation

import com.senai.carteirinhavini.feature.professor.turmas.domain.model.TurmaProfessor

data class TurmasProfessorUiState(
    val isLoading: Boolean = false,
    val turmas: List<TurmaProfessor> = emptyList(),
    val errorMessage: String? = null
)
