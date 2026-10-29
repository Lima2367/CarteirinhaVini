package com.senai.carteirinhavini.feature.professor.unidadescurriculares.domain.repository

import com.senai.carteirinhavini.feature.professor.unidadescurriculares.domain.model.UnidadeCurricularProfessor

interface UnidadeCurricularProfessorRepository {
    suspend fun listarMinhasUnidadesCurriculares(): Result<List<UnidadeCurricularProfessor>>
}
