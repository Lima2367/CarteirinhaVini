package com.senai.carteirinhavini.feature.professor.unidadescurriculares.data.repository

import com.senai.carteirinhavini.feature.professor.unidadescurriculares.data.remote.service.UnidadeCurricularProfessorApi
import com.senai.carteirinhavini.feature.professor.unidadescurriculares.domain.model.UnidadeCurricularProfessor
import com.senai.carteirinhavini.feature.professor.unidadescurriculares.domain.repository.UnidadeCurricularProfessorRepository
import retrofit2.HttpException
import java.io.IOException

class ApiUnidadeCurricularProfessorRepositoryImpl(
    private val api: UnidadeCurricularProfessorApi
) : UnidadeCurricularProfessorRepository {
    override suspend fun listarMinhasUnidadesCurriculares(): Result<List<UnidadeCurricularProfessor>> = runCatching {
        api.listarMinhasUnidadesCurriculares().map { it.toDomain() }
    }.recoverCatching { throwable ->
        throw when (throwable) {
            is HttpException -> when (throwable.code()) {
                401 -> IllegalStateException("Sua sessão expirou. Entre novamente no aplicativo.")
                403 -> IllegalStateException("Esta área está disponível apenas para professores.")
                else -> IllegalStateException("Erro ao carregar unidades curriculares (${throwable.code()}).")
            }
            is IOException -> IllegalStateException(
                "Não foi possível conectar à API. Confira se o servidor está rodando e se o endereço está correto."
            )
            else -> IllegalStateException(throwable.message ?: "Erro ao carregar unidades curriculares.")
        }
    }
}
