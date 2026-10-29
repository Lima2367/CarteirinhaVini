package com.senai.carteirinhavini.feature.professor.unidadescurriculares.data.remote.service

import com.senai.carteirinhavini.feature.professor.unidadescurriculares.data.remote.dto.UnidadeCurricularProfessorDto
import retrofit2.http.GET

interface UnidadeCurricularProfessorApi {
    @GET("professores/me/unidades-curriculares")
    suspend fun listarMinhasUnidadesCurriculares(): List<UnidadeCurricularProfessorDto>
}
