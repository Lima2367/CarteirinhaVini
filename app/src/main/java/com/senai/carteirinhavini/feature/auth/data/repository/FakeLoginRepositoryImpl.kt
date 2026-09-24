package com.senai.carteirinhavini.feature.auth.data.repository

import com.senai.carteirinhavini.feature.auth.domain.model.UsuarioLogado
import kotlinx.coroutines.delay

class FakeLoginRepositoryImpl : LoginRepository {

    override suspend fun login(
        usuario: String,
        senha: String
    ): Result<UsuarioLogado> {

        delay(1500)

        return if (usuario.equals("aluno") && senha.equals("123")) {
            Result.success(
            UsuarioLogado(
                id = "12-3",
                nome = "Lima",
                curso = "Desenvolvimento de Sistema",
                turma = "2DEVEST-A",
                token = "token-fake-vindo-do sistema"
            )
            )
        } else {
            Result.failure(
                IllegalArgumentException("Login ou senha inválidos!!")
            )
        }
    }
}