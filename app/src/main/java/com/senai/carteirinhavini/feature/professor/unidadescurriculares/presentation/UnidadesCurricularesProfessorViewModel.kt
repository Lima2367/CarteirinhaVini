package com.senai.carteirinhavini.feature.professor.unidadescurriculares.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.carteirinhavini.feature.professor.unidadescurriculares.domain.repository.UnidadeCurricularProfessorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UnidadesCurricularesProfessorViewModel(
    private val repository: UnidadeCurricularProfessorRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UnidadesCurricularesProfessorUiState())
    val uiState: StateFlow<UnidadesCurricularesProfessorUiState> = _uiState.asStateFlow()

    fun carregar() {
        if (_uiState.value.isLoading) return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            repository.listarMinhasUnidadesCurriculares()
                .onSuccess { unidades ->
                    _uiState.update { it.copy(isLoading = false, unidades = unidades, errorMessage = null) }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.message ?: "Não foi possível carregar as unidades curriculares."
                        )
                    }
                }
        }
    }
}
