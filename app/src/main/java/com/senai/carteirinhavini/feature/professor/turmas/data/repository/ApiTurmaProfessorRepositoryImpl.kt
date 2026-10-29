package com.senai.carteirinhavini.feature.professor.turmas.data.repository

import com.senai.carteirinhavini.feature.professor.turmas.data.remote.service.TurmaProfessorApi
import com.senai.carteirinhavini.feature.professor.turmas.domain.model.TurmaProfessor
import com.senai.carteirinhavini.feature.professor.turmas.domain.repository.TurmaProfessorRepository
import retrofit2.HttpException
import java.io.IOException

class ApiTurmaProfessorRepositoryImpl(
    private val api: TurmaProfessorApi
) : TurmaProfessorRepository {
    override suspend fun listarMinhasTurmas(): Result<List<TurmaProfessor>> = runCatching {
        api.listarMinhasTurmas().map { it.toDomain() }
    }.recoverCatching { throwable ->
        throw when (throwable) {
            is HttpException -> when (throwable.code()) {
                401 -> IllegalStateException("Sua sessão expirou. Entre novamente no aplicativo.")
                403 -> IllegalStateException("Esta área está disponível apenas para professores.")
                else -> IllegalStateException("Erro ao carregar suas turmas (${throwable.code()}).")
            }
            is IOException -> IllegalStateException(
                "Não foi possível conectar à API. Confira se o servidor está rodando e se o endereço está correto."
            )
            else -> IllegalStateException(throwable.message ?: "Erro ao carregar suas turmas.")
        }
    }
}
