package com.senai.carteirinhavini.feature.auth.presentation

sealed interface LoginEvent {
    data class OnUsuarioChange(val value: String) : LoginEvent

    data class OnSenhaChange(val value: String) : LoginEvent

    data object  OnEntrarClick: LoginEvent
    data object OnNavegacaoRealizado: LoginEvent

}