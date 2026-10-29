package com.senai.carteirinhavini.feature.professor.turmas.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.senai.carteirinhavini.feature.professor.turmas.domain.repository.TurmaProfessorRepository
import com.senai.carteirinhavini.feature.professor.turmas.presentation.TurmasProfessorViewModel

class TurmasProfessorViewModelFactory(
    private val repository: TurmaProfessorRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (!modelClass.isAssignableFrom(TurmasProfessorViewModel::class.java)) {
            throw IllegalArgumentException("ViewModel desconhecido: ${modelClass.name}")
        }
        return TurmasProfessorViewModel(repository) as T
    }
}
