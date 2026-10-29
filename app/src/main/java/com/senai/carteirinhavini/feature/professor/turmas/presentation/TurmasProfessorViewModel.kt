package com.senai.carteirinhavini.feature.professor.turmas.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.carteirinhavini.feature.professor.turmas.domain.repository.TurmaProfessorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TurmasProfessorViewModel(
    private val repository: TurmaProfessorRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(TurmasProfessorUiState())
    val uiState: StateFlow<TurmasProfessorUiState> = _uiState.asStateFlow()

    fun carregar() {
        if (_uiState.value.isLoading) return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            repository.listarMinhasTurmas()
                .onSuccess { turmas ->
                    _uiState.update { it.copy(isLoading = false, turmas = turmas, errorMessage = null) }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.message ?: "Não foi possível carregar suas turmas."
                        )
                    }
                }
        }
    }
}
