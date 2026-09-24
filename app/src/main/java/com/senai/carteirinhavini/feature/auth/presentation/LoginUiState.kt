package com.senai.carteirinhavini.feature.auth.presentation

import com.senai.carteirinhavini.feature.auth.domain.model.UsuarioLogado

data class LoginUiState(
    val usuario: String = "",
    val senha: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val usuarioLogado: UsuarioLogado? = null
){
    val loginRealizado: Boolean
        get() = usuarioLogado != null
}