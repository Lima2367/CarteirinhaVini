package com.senai.carteirinhavini.feature.professor.unidadescurriculares.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.senai.carteirinhavini.feature.professor.unidadescurriculares.domain.repository.UnidadeCurricularProfessorRepository
import com.senai.carteirinhavini.feature.professor.unidadescurriculares.presentation.UnidadesCurricularesProfessorViewModel

class UnidadesCurricularesProfessorViewModelFactory(
    private val repository: UnidadeCurricularProfessorRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (!modelClass.isAssignableFrom(UnidadesCurricularesProfessorViewModel::class.java)) {
            throw IllegalArgumentException(
                "ViewModel desconhecido: ${modelClass.name}"
            )
        }

        @Suppress("UNCHECKED_CAST")
        return UnidadesCurricularesProfessorViewModel(repository) as T
    }
}
