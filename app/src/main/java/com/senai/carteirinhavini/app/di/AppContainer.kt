package com.senai.carteirinhavini.app.di

import com.senai.carteirinhavini.core.auth.SessionTokenStore
import com.senai.carteirinhavini.feature.unidadecurriculares.domain.repository.UnidadeCurricularRepository
import com.senai.carteirinhavini.feature.login.domain.repository.LoginRepository
import com.senai.carteirinhavini.feature.professor.turmas.domain.repository.TurmaProfessorRepository
import com.senai.carteirinhavini.feature.professor.unidadescurriculares.domain.repository.UnidadeCurricularProfessorRepository

interface AppContainer {
    val sessionTokenStore: SessionTokenStore
    val loginRepository: LoginRepository
    val unidadeCurricularRepository: UnidadeCurricularRepository
    val turmaProfessorRepository: TurmaProfessorRepository
    val unidadeCurricularProfessorRepository: UnidadeCurricularProfessorRepository
}
