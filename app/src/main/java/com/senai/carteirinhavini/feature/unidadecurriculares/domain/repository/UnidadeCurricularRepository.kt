package com.senai.carteirinhavini.feature.unidadecurriculares.domain.repository

import com.senai.carteirinhavini.feature.unidadecurriculares.domain.model.UnidadeCurricular

interface UnidadeCurricularRepository {
    suspend fun listar():Result<List<UnidadeCurricular>>
}