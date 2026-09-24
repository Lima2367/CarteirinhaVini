package com.senai.carteirinhavini.feature.auth.data.repository

import com.senai.carteirinhavini.feature.auth.domain.model.UsuarioLogado

interface LoginRepository {
    suspend fun login(usuario: String, senha:String): Result<UsuarioLogado>
}