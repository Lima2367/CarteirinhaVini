package com.senai.carteirinhavini.feature.professor.turmas.domain.repository

import com.senai.carteirinhavini.feature.professor.turmas.domain.model.TurmaProfessor

interface TurmaProfessorRepository {
    suspend fun listarMinhasTurmas(): Result<List<TurmaProfessor>>
}
